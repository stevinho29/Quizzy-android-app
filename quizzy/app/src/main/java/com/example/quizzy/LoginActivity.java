package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.utils.Constants;
import com.example.quizzy.utils.PreferenceUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private EditText mUserEdit;
    private EditText mPasswordEdit;
    private Button register;
    UserDatabase db= QuizzyApplication.getDb();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzy_login);
        Toolbar myToolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(myToolbar);

        mUserEdit =  findViewById(R.id.usernameEditText); // le username  correspond au pseudo de l'utilisateur
        mPasswordEdit =  findViewById(R.id.loginEditText);
        register =  findViewById(R.id.signinButton);

        findViewById(R.id.LoginButton).setOnClickListener(this);
        register.setOnClickListener(this);
        try {
            final String username = PreferenceUtils.getUsername();
            if(!TextUtils.isEmpty(username)) {
                Toast.makeText(QuizzyApplication.getContext(),"Connected",Toast.LENGTH_SHORT).show();
                startActivity(getUserIntent(username));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        progressBar= findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.VISIBLE);
        if(v.getId()== R.id.LoginButton) {
            if (TextUtils.isEmpty(mUserEdit.getText())) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Empty Username", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            String username = mUserEdit.getText().toString();
            if (TextUtils.isEmpty(mPasswordEdit.getText())) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Empty Password", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            String password = mPasswordEdit.getText().toString();


            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new Runnable() {

                @Override
                public void run() {

                    if (db.UserDao().getAutehenticatedUser(username, password) == null) {

                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(QuizzyApplication.getContext(), "Connected", Toast.LENGTH_SHORT).show();
                                //PreferenceUtils.setUsername(username);
                                startActivity(getUserIntent(username));
                            }
                        });

                    } else {
                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(QuizzyApplication.getContext(), "You seems not to be registered yet...please sign in", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });

                    }
                }
            });
        }
        if(v.getId() == R.id.signinButton){
            progressBar.setVisibility(View.INVISIBLE);
            startActivity(getRegisterIntent());
        }


    }
    private Intent getUserIntent(String userName){
        final Intent homeIntent = new Intent(this, UserActivity.class);
        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        homeIntent.putExtras(extras);
        return homeIntent;
    }


    private Intent getRegisterIntent(){
        final Intent registerIntent= new Intent(this,RegisterActivity.class);
        return registerIntent;
    }



}
