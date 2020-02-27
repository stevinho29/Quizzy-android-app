package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultatActivity extends AppCompatActivity {

    String score = null;
    public TextView scoreText;

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
            }
        }
        if(score == null){
            score = "";
        }
        scoreText = findViewById(R.id.score);
        scoreText.setText(score);
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
