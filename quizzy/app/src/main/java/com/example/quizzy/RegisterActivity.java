package com.example.quizzy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.CheckBox;

import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzy.jobs.PersistData;
import com.example.quizzy.model.Repository.UserDatabase;


import com.example.quizzy.model.entities.User;
import com.example.quizzy.utils.Constants;

import com.example.quizzy.jobs.RegisterValidateForm;
import com.example.quizzy.utils.PreferenceUtils;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener, DatePickerDialog.OnDateSetListener  {

    UserDatabase db;
    private EditText pseudo;
    private EditText name;
    private EditText surname;
    private EditText email;
    private TextView birhtDate;
    private EditText password;
    private EditText confirm_password;
    private Button   valider;
    private CheckBox rgpd;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = QuizzyApplication.getDb();
        pseudo = findViewById(R.id.pseudoEdit);
       name= findViewById(R.id.nameEdit);
       surname= findViewById(R.id.surnameEdit);
       email= findViewById(R.id.emailEdit);
       birhtDate= findViewById(R.id.birthEdit);
       password= findViewById(R.id.passwordEdit);
       confirm_password= findViewById(R.id.confirmPasswordEdit);
       valider= findViewById(R.id.registerButton);
       rgpd= findViewById(R.id.checkBox);

       valider.setOnClickListener(this);
       birhtDate.setOnTouchListener(this);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {


            if (TextUtils.isEmpty(pseudo.getText())) {
                name.setError("le champ pseudo est vide");
                return;
            }
            if (TextUtils.isEmpty(name.getText())) {
                name.setError("le champ nom est vide");
                return;
            }
            if (TextUtils.isEmpty(surname.getText())) {
                surname.setError("le champ prénom est vide");
                return;
            }
            if (TextUtils.isEmpty(email.getText())) {
                email.setError("le champ email est vide");
                return;
            }
            if (!RegisterValidateForm.isValidEmail(email.getText().toString())) {
                email.setError("saisissez une adresse email valide ");
                return;
            }
            if (TextUtils.isEmpty(password.getText())) {
                password.setError("le champ mot de passe est vide");
                return;
            }
            if (TextUtils.isEmpty(confirm_password.getText())) {
                confirm_password.setError("le champ de confirmation de mot de passe est vide");
                return;
            }
            /*
            if(RegisterValidateForm.isValidDate(birhtDate.getDate())){
                Toast.makeText(QuizzyApplication.getContext(),"vous devez etre agé d'au moins 10 ans",Toast.LENGTH_SHORT).show();
                return;
            }*/

            if (!RegisterValidateForm.isValidMatchPassword(password.getText().toString(), confirm_password.getText().toString())) {
                Toast.makeText(QuizzyApplication.getContext(), "les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                password.setError("error");
                confirm_password.setError("error");
                return;
            }
            if (!rgpd.isChecked()) {
                Toast.makeText(QuizzyApplication.getContext(), "Vous devez accepter les conditions d'utilisation", Toast.LENGTH_SHORT).show();
                return;
            }

            User user= new User(name.getText().toString(),surname.getText().toString(),email.getText().toString(),password.getText().toString(),date);
            try{
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        PersistData.userInfoTodatabase(user);
                    }
                });

            }catch (Exception e){
                e.printStackTrace();

                    Toast.makeText(QuizzyApplication.getContext(),"adresse mail et/ou pseudo deja utilisé",Toast.LENGTH_SHORT).show();

            }
            Toast.makeText(QuizzyApplication.getContext(), "Registered.....", Toast.LENGTH_SHORT).show();
            PreferenceUtils.setUsername(pseudo.getText().toString());
            clearAllFields();
            startActivity(getUserIntent(pseudo.getText().toString()));

    }

    private Intent getUserIntent(String userName){
        final Intent homeIntent = new Intent(this, UserActivity.class);
        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        homeIntent.putExtras(extras);
        return homeIntent;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Calendar c = Calendar.getInstance();
        int year= c.get(Calendar.YEAR);
        int month= c.get(Calendar.MONTH);
        int day= c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog= new DatePickerDialog(this,this,year,month,day);
       datePickerDialog.show();

        return false;
    }

    private void clearAllFields(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,c.get(Calendar.YEAR));
        c.set(Calendar.MONTH,c.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH));

        name.getText().clear();
        surname.getText().clear();
        email.getText().clear();
        password.getText().clear();
        confirm_password.getText().clear();
        birhtDate.setText(DateFormat.getDateInstance().format(c.getTime()));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        date= c.getTime();
        birhtDate.setText(DateFormat.getDateInstance().format(c.getTime()));
    }
}
