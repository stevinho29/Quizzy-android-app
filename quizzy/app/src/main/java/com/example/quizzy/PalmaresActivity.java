package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.quizzy.adapter.PalmaresCategoryAdapter;
import com.example.quizzy.interfaces.CategoryListener;
import com.example.quizzy.interfaces.SelectListener;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PalmaresActivity extends AppCompatActivity  implements CategoryListener {
    UserDatabase db;
    PalmaresCategoryAdapter adapter;
    List<Category> categories;
    RecyclerView rv;
    SelectListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palmares);
    }

    @Override
    protected void onStart() {
        super.onStart();
        db = QuizzyApplication.getDb();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                List<Category> categoryList= db.CategoryDao().getAllCategory();
                onCategoryRetrieved(categoryList);
            }
        });
    }

    public void onCategoryRetrieved(List<Category> categoryList) {
        adapter = new PalmaresCategoryAdapter(categoryList, this);
        rv = findViewById(R.id.palmares_category_recycler);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rv.setAdapter(adapter);

        adapter.setCategoryListener(mListener);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCategory(String categoryLibelle) {
        //startActivity(getPartyIntent(categoryLibelle));
    }
}
