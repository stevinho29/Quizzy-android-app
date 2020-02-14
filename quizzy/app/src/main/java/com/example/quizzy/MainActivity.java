package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.Question;
import com.example.quizzy.model.entities.User;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.LocalDate.now;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "user_bdd.db").build();

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
        executor.submit(new Runnable() {
            @Override
            public void run() {
                List<Category> listCategory = db.CategoryDao().getAllCategory();
                for(Category c: listCategory){
                    Log.d("Quizz Category",c.getLibelleCategory());
                    System.out.println("Quizz Category: "+c.getLibelleCategory());
                }
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                List<Question> listQuestion = db.QuestionDao().getSpecificQuestion(1);
                for(Question q: listQuestion){
                    Log.d("Quizz Questions",q.getLibelleQuestion());
                }
            }
        });
    }
}
