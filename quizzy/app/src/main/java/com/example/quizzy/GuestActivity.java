package com.example.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.adapter.CategoryAdapter;
import com.example.quizzy.interfaces.CategoryListener;
import com.example.quizzy.interfaces.SelectListener;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.CategoryAndQuestions;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuestActivity extends AppCompatActivity implements CategoryListener{ // activité lancée quand tu choisis le mode invité

    UserDatabase db;
    CategoryAdapter adapter;
    RecyclerView rv;
    SelectListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

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
                List<Category> categoryList= db.CategoryDao().getAllCategory();
                List<Category> secondList = null;
                List<CategoryAndQuestions> categoryAndQuestionsList;
                int size= 0;
                for( Category c: categoryList){
                     categoryAndQuestionsList = db.CategoryDao().getCategoryAndQuestions(c.getLibelleCategory());
                     size= categoryAndQuestionsList.get(0).questionList.size();
                    if( size >= 10) {
                         secondList.add(c);
                         Log.d("catégorie ayant > 10",c.getLibelleCategory());
                     }
                }

                onCategoryRetrieved(categoryList);
            }
        });
    }

    public void onCategoryRetrieved(List<Category> categoryList) {

        adapter = new CategoryAdapter(categoryList, this);
        rv = findViewById(R.id.home_recycler);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        adapter.setCategoryListener(mListener);
        adapter.notifyDataSetChanged();
    }




    private Intent getPartyIntent(String libelle){
        final Intent partyIntent = new Intent(this,PartyActivity.class);
        final Bundle extras = new Bundle();
        extras.putString("libelle", libelle);
        partyIntent.putExtras(extras);
        return partyIntent;
    }

    /*@Override
    public void onCategorySelected(String libelle) {
        startActivity(getPartyIntent(libelle));
    }*/

    @Override
    public void onViewCategory(String categoryLibelle) {
        startActivity(getPartyIntent(categoryLibelle));
    }
}

