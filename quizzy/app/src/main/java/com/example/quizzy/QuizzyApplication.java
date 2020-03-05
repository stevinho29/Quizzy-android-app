package com.example.quizzy;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

import androidx.room.Room;

import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class QuizzyApplication extends Application {
    private static Context sContext;

    static UserDatabase db;
    static List<Category> permanentCategoryList= new ArrayList<>();


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

    public static List<Category> getPermanentCategoryList(){
        return permanentCategoryList;
    }
    public static void setPermanentCategoryList(List<Category> list){
        permanentCategoryList= list;
    }
}
