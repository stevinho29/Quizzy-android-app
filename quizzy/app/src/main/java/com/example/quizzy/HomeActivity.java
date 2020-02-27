package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.quizzy.adapter.CategoryAdapter;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity {

    /*UserDatabase db;
    CategoryAdapter adapter;
    List<Category> categories;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        categories = new ArrayList<>();

        adapter = new CategoryAdapter(categories);
        rv = findViewById(R.id.home_recycler);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        db = QuizzyApplication.getDb();
        //final String login = PreferenceUtils.getUsername();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                onCategoryRetrieved(db.CategoryDao().getAllCategory());
            }
        });
    }

    public void onCategoryRetrieved(List<Category> categoryList) {
        categories.addAll(categoryList);
        adapter.notifyDataSetChanged();
    }*/
}
