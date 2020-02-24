package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quizzy.api.RetrofitClientInstance;
import com.example.quizzy.async.GetDataService;
import com.example.quizzy.jobs.PersistData;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.Question;
import com.example.quizzy.pojo.IncomingJson;
import com.example.quizzy.ui.fragments.QuestionsFragment;
import com.example.quizzy.utils.Constants;
import com.example.quizzy.utils.PreferenceUtils;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db = QuizzyApplication.getDb();

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  login = extras.getString(Constants.Login.EXTRA_LOGIN);
                try {
                    getSupportActionBar().setSubtitle(login);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }
        }
       /* if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new QuestionsFragment()).commit();
        }*/


       /* ExecutorService executor = Executors.newSingleThreadExecutor();

        //RetrieveQuizzData quizz= new RetrieveQuizzData();
        //quizz.execute();

        *//*Create handle for the RetrofitInstance interface*//*
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<IncomingJson> call = service.getApiQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                    Log.d("Retrofit response", response.body().results.toString());
                    executor.submit(new Runnable() {
                        @Override
                        public void run() {

                            PersistData.quizzInfoToDatabase(response.body().results,db);
                        }
                    });

            }

            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR", "didn't get a shit from API");
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        executor.submit(new Runnable() {
            @Override
            public void run() {
                List<Category> listCategory = db.CategoryDao().getAllCategory();
                for(Category c: listCategory){
                    Log.d("Quizz Category",c.getLibelleCategory());
                    Log.d("Quizz Category ID ",c.getId_category().toString());

                }
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                Category category = db.CategoryDao().getCategoryById(1);
                Question question = db.QuestionDao().getSpecificQuestion(1);
                List<Question> listQuestion = db.QuestionDao().getAllQuestion();
                for(Question q: listQuestion){

                    Log.d("Quizz Questions",q.getLibelleQuestion());
                }


            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //action de logout
        if (id == R.id.actionLogout) {
            PreferenceUtils.setUsername(null);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
