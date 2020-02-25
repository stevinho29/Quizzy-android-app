package com.example.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.ui.fragments.CategoriesFragment;
import com.example.quizzy.utils.Constants;

public class GuestActivity extends AppCompatActivity { // activité lancée quand tu choisis le mode invité

    UserDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guest);

        db = QuizzyApplication.getDb();

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new CategoriesFragment()).commit();
        }
    }

}

