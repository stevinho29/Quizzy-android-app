package com.example.quizzy;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

import androidx.room.Room;

import com.example.quizzy.model.Repository.UserDatabase;

public class QuizzyApplication extends Application {
    private static Context sContext;

    static UserDatabase db;



    public void onCreate(){
        super.onCreate();

        // Keep a reference to the application context
        sContext = getApplicationContext();

        db = Room.databaseBuilder(this, UserDatabase.class, "quizzy_bdd.db")
                .build();
    }

    // Used to access Context anywhere within the app
    public static Context getContext() {
        return sContext;
    }

    public static UserDatabase getDb(){
        return db;
    }


}
