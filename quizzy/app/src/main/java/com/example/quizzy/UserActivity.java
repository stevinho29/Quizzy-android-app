package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.quizzy.utils.Constants;
import com.example.quizzy.utils.PreferenceUtils;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {  // activité lancée après authentification de l'utilisateur

    private Toolbar bar;
    private Button historique;
    private Button startParty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bar= findViewById(R.id.app_bar);
        Toolbar myToolbar = findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  pseudo = extras.getString(Constants.Login.EXTRA_LOGIN);
                try {
                    bar.setTitle("welcome back "+pseudo);
                    getSupportActionBar().setSubtitle(pseudo);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }
        }
        historique= findViewById(R.id.userHistory);
        startParty= findViewById(R.id.userQuizz);
        historique.setOnClickListener(this);
        startParty.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //action de logout
        if (id == R.id.actionLogout) {
            PreferenceUtils.setUsername(null);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.userQuizz){
            startActivity(getGuestIntent());
        }else{ // dans ce cas c'est l'historique qu'on affiche
            startActivity(getPalmaresIntent());
        }
    }

    public Intent getGuestIntent(){
        return new Intent(this,GuestActivity.class);
    }
    public Intent getPalmaresIntent(){
        return new Intent(this,PalmaresActivity.class);
    }
}
