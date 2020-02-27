package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.quizzy.utils.PreferenceUtils;

public class ResultatActivity extends AppCompatActivity {

    String score = null;
    String libelle = null;
    public TextView scoreText;
    private TextView greetings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

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
            score ="Aucune réponse juste";

        greetings=findViewById(R.id.greetingsTextView);
        if(PreferenceUtils.getUsername() != null)
            greetings.setText("Félicitations "+PreferenceUtils.getUsername());

        scoreText = findViewById(R.id.score);
        scoreText.setText(score+"\n"+libelle);
        View.OnClickListener clickListener = v -> {
            startActivity(getGuestIntent());
        };
        findViewById(R.id.newParty).setOnClickListener(clickListener);
    }

    private Intent getGuestIntent(){
        final Intent GuestIntent = new Intent(this, GuestActivity.class);
        return GuestIntent;
    }
}
