package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizzy.adapter.CategoryAdapter;
import com.example.quizzy.pojo.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        /*List<Category> categories = new ArrayList<>();
        Category category = new Category("e", "e", "e", "e", "e", new ArrayList<>());

        categories.add(category);
        CategoryAdapter adapter = new CategoryAdapter(categories);
        RecyclerView rv = findViewById(R.id.home_recycler);
        rv.setAdapter(adapter);*/

    }
}
