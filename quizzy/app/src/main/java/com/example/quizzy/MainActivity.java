package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "quizzy_bdd.db")
                .build();

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  login = extras.getString(Constants.Login.EXTRA_LOGIN);
                getSupportActionBar().setSubtitle(login);

            }
        }
       /* if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new QuestionsFragment()).commit();
        }*/



        ExecutorService executor = Executors.newSingleThreadExecutor();
        /*executor.submit(new Runnable() {
            @Override
            public void run() {

                User user= new User();
                user.setId_user(1);
                user.setName("aName");
                user.setSurname("aSurname");
                user.setBirthDate(new Date());


                db.UserDao().insertUser(user);
            }
        });*/
       /* executor.submit(new Runnable() {
            @Override
            public void run() {

                Category category= new Category(1,"Drame");
                Category c1= new Category(2,"Horrorr");
                Category c2= new Category(3,"Animé");
                db.CategoryDao().insertCategory(category);
                db.CategoryDao().insertCategory(c1);
                db.CategoryDao().insertCategory(c2);
            }
        });*/
        /*executor.submit(new Runnable() {
            @Override
            public void run() {

                Question question= new Question(1,"quel est le meilleur filme Drama",1);
                Question q1= new Question(2,"Le drame est il la meilleure des choses",1);
                Question q2= new Question(3,"Don't know what to say",2);
                db.QuestionDao().insertQuestion(question);
                db.QuestionDao().insertQuestion(q1);
                db.QuestionDao().insertQuestion(q2);
            }
        });*/
        //RetrieveQuizzData quizz= new RetrieveQuizzData();
        //quizz.execute();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<IncomingJson> call = service.getApiQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                    Log.d("Retrofit response", response.body().results.toString());
                    executor.submit(new Runnable() {
                        @Override
                        public void run() {

                            PersistData.toDatabase(response.body().results,db);
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
                System.out.println("DDDDDDDDDje teste"+category.getLibelleCategory());
                Question question = db.QuestionDao().getSpecificQuestion(1);
                List<Question> listQuestion = db.QuestionDao().getAllQuestion();
                for(Question q: listQuestion){

                    Log.d("Quizz Questions",q.getLibelleQuestion());
                }

                System.out.println("Category: "+category.getLibelleCategory()+" id categ: "+category.getId_category());
                System.out.println("question associée"+question.getLibelleQuestion());
            }
        });
    }
}
