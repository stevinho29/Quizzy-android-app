package com.example.quizzy;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.adapter.CategoryAdapter;
import com.example.quizzy.interfaces.CategoryListener;
import com.example.quizzy.interfaces.SelectListener;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.CategoryAndQuestions;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class GuestActivity extends AppCompatActivity implements CategoryListener{ // activité lancée quand tu choisis le mode invité

    UserDatabase db;
    CategoryAdapter adapter;
    RecyclerView rv;
    SelectListener mListener;
    private static MediaPlayer mediaPlayer;

    private static List<Category> categoryList;
    private static List<Category> secondList;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        //progressBar= findViewById(R.id.progressBarCategory);

    }

    @Override
    protected void onStart() {
        super.onStart();
        db = QuizzyApplication.getDb();
        //final String login = PreferenceUtils.getUsername();

        getCategory();

    }

    public void getCategory(){

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                List<Category> categoryList= db.CategoryDao().getAllCategory();
                onCategoryRetrieved(categoryList);
            }
        });

    }
    public void onCategoryRetrieved(List<Category> categoryList) {

        if(mediaPlayer != null)
            mediaPlayer.release();
        onStartMusic();

        //progressBar.setVisibility(View.GONE);
        adapter = new CategoryAdapter(categoryList, this);
        rv = findViewById(R.id.home_recycler);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }




    private Intent getPartyIntent(String libelle){
        final Intent partyIntent = new Intent(this,PartyActivity.class);
        partyIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        final Bundle extras = new Bundle();
        extras.putString("libelle", libelle);
        partyIntent.putExtras(extras);
        return partyIntent;
    }

    /*@Override
    public void onCategorySelected(String libelle) {
        startActivity(getPartyIntent(libelle));
    }*/

    @Override
    public void onViewCategory(String categoryLibelle) {
        releaseMediaPlayer();
        startActivity(getPartyIntent(categoryLibelle));
    }

    public  void onStartMusic(){
        int jingleId= getResources().getIdentifier("jingle","raw",getPackageName());
        mediaPlayer= MediaPlayer.create(GuestActivity.this,jingleId);
        mediaPlayer.start();
    }
    public static void releaseMediaPlayer(){
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        releaseMediaPlayer();
        //finish();
    }
}

