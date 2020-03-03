package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizzy.api.RetrofitClientInstance;
import com.example.quizzy.async.GetDataService;
import com.example.quizzy.jobs.FirstLoadFromApi;
import com.example.quizzy.jobs.PersistData;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.Question;
import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.model.entities.User;
import com.example.quizzy.pojo.IncomingJson;
import com.example.quizzy.utils.PreferenceUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener { // première activité de l'application

    private Button guest;
    private  Button user;
    private UserDatabase db= QuizzyApplication.getDb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db= QuizzyApplication.getDb();
        guest= findViewById(R.id.guestButton);
        user= findViewById(R.id.userButton);

        guest.setOnClickListener(this);
        user.setOnClickListener(this);

       // PreferenceUtils.deletePrefs();

        if(!PreferenceUtils.getFirstTime()) {
            PreferenceUtils.setFirstTime(true);
            FirstLoadFromApi getOurFirstQuestion = new FirstLoadFromApi();
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();


        ///*Create handle for the RetrofitInstance interface*//*
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<IncomingJson> call = service.getApiQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                    Log.d("Retrofit response", response.body().results.toString());
                    executor.submit(new Runnable() {
                        @Override
                        public void run() {

                            PersistData.quizzInfoToDatabase(response.body().results);
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

                Question question = db.QuestionDao().getSpecificQuestion(1);
                Log.d("Quizz Question",question.getLibelleQuestion());
                //List<Question> listQuestion = db.QuestionDao().getAllQuestion();
                /*for(Question q: questionList){

                    Log.d("Quizz Questions",q.getLibelleQuestion());
                }*/


            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {


                List<User> listUser = db.UserDao().getAllUser();
                for(User u: listUser){

                    Log.d("Quizz User",u.getName());
                }


            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {


                List<ReponseFausse> list= db.ReponseFausseDao().getAllResponseFalseForAQuestion(1);
                if(list.size() != 0){
                for(ReponseFausse p: list){

                    Log.d("Quizz reponse",p.getLibelleReponseFausse());
                }}


            }
        });
    }




    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.guestButton){
            startActivity(getGuestIntent());
        }
        else{
            startActivity(getLoginIntent());
        }
    }
    private Intent getLoginIntent(){
        final Intent LoginIntent = new Intent(this, LoginActivity.class);
        return LoginIntent;
    }
    private Intent getGuestIntent(){
        final Intent GuestIntent = new Intent(this, GuestActivity.class);
        return GuestIntent;
    }
    private Intent getHomeIntent(){
        final Intent HomeIntent = new Intent(this, HomeActivity.class);
        return HomeIntent;
    }
}
