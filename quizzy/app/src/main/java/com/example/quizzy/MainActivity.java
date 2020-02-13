package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.User;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.LocalDate.now;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "quizzy_bdd.db").build();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {

                User user= new User();
                user.setId_user(1);
                user.setName("aName");
                user.setSurname("aSurname");
                user.setBirthDate(new Date());


                db.UserDao().insertUser(user);
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                List<User> listUser = db.UserDao().getAllUser("aName");
                for(User u: listUser){
                    Log.d("Quizz BDD",u.getName());
                }
            }
        });
    }
}
