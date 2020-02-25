package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.quizzy.ui.fragments.CategoriesFragment;
import com.example.quizzy.utils.Constants;
import com.example.quizzy.utils.PreferenceUtils;

public class UserActivity extends AppCompatActivity {  // activité lancée après authentification de l'utilisateur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  login = extras.getString(Constants.Login.EXTRA_LOGIN);
                try {
                    getSupportActionBar().setSubtitle(login);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }
        }
         if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new CategoriesFragment()).commit();
        }
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
}
