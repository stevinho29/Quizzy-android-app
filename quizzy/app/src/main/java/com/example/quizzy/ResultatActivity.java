package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Jouer;
import com.example.quizzy.model.entities.Party;
import com.example.quizzy.utils.PreferenceUtils;

import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResultatActivity extends AppCompatActivity {

    static String score = null;
    static String libelle = null;
    public TextView scoreText;
    private TextView greetings;
    private static MediaPlayer mediaPlayer;
    private static UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        db= QuizzyApplication.getDb();

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                score = extras.getString("score");
                libelle = extras.getString("libelle");
            }
        }
        if(score == null)
            score ="05/10";
        if(mediaPlayer != null)
            mediaPlayer.release();

        scoreText = findViewById(R.id.score);
        scoreText.setText(score+"\n"+libelle);

        onDisplayResultMusic(); // générique de fin

        greetings=findViewById(R.id.greetingsTextView);
        if(PreferenceUtils.getUsername() != null) {
            greetings.setText("Félicitations " + PreferenceUtils.getUsername());

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    saveParty();                // on sauvegarde la partie
                    Toast.makeText(ResultatActivity.this,"score saved",Toast.LENGTH_SHORT).show();
                }
            });
        }

        View.OnClickListener clickListener = v -> {
            releaseMediaPlayer();
            startActivity(getGuestIntent());
            finish();
        };
        findViewById(R.id.newParty).setOnClickListener(clickListener);
    }

    private Intent getGuestIntent(){
        final Intent GuestIntent = new Intent(this, GuestActivity.class);
        return GuestIntent;
    }

    private void saveParty(){
        Integer s= Integer.valueOf(score.substring(0,score.indexOf("/")));
        Integer id_c= db.CategoryDao().getCategoryBylibelle("Entertainment: Books").getId_category();
        Integer id_u= db.UserDao().getAUserByPseudo(PreferenceUtils.getUsername()).getId_user();
        Date date;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,c.get(Calendar.YEAR));
        c.set(Calendar.MONTH,c.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH));
        date=  java.sql.Date.valueOf(c.toString());
        Party party= new Party((int)10*10, s, date,id_c);
        db.PartyDao().insertParty(party);
        Integer id_p= db.PartyDao().getAPartyByCategory(id_c).getId_party();
        db.jouerDao().insertPartyAndUser(new Jouer(party.getId_party(),id_u));
    }

    private void onDisplayResultMusic(){
        int resultMusicId= getResources().getIdentifier("traffic_racro2","raw",getPackageName());
        mediaPlayer= MediaPlayer.create(ResultatActivity.this,resultMusicId);
        mediaPlayer.start();
    }
    public static void releaseMediaPlayer(){
        if(mediaPlayer != null) {
            if(mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        releaseMediaPlayer();
        finish();
    }
}
