package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quizzy.adapter.AnswerAdapter;
import com.example.quizzy.adapter.CategoryAdapter;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.CategoryAndQuestions;
import com.example.quizzy.model.entities.Party;
import com.example.quizzy.model.entities.Question;
import com.example.quizzy.model.entities.QuestionAndReponseVraie;
import com.example.quizzy.model.entities.QuestionAndReponsesFalse;
import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.model.entities.ReponseVraie;
import com.example.quizzy.utils.Constants;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PartyActivity extends AppCompatActivity implements View.OnClickListener {

    UserDatabase db;
    private List<CategoryAndQuestions> categoryAndQuestionsList;
    private List<Question> questionList;

    private List<ReponseFausse> reponseFausseList;
    private ReponseVraie reponseVraie;


    private Button start;
    private ProgressBar jauge;
    private TextView question;

    private CountDownTimer countDownTimer=null;
    private boolean adapterAlreadySet= false;
    private Toolbar bar;
    private Integer currentIndex =1;
    AnswerAdapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        db= QuizzyApplication.getDb();


        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  libelle = extras.getString("libelle");
            }
        }
        getQuestionForCurrentCategory("Entertainment: Board Games");
        bar= findViewById(R.id.app_bar);
        question= findViewById(R.id.question);
        jauge= findViewById(R.id.determinateBar);
        start=findViewById(R.id.startQuiz);
        start.setOnClickListener(this);

    }

    public void getQuestionForCurrentCategory(String libelle){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                categoryAndQuestionsList= db.CategoryDao().getCategoryAndQuestions(libelle);
                questionList= categoryAndQuestionsList.get(0).questionList;
                //Log.d("Category et questions",categoryAndQuestionsList.get(0).questionList.get(0).getLibelleQuestion());

            }
        });

    }
    public boolean getCurrentAnswers(Question q){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                reponseFausseList = db.QuestionDao().getQuestionAndResponseFalse(q.getLibelleQuestion()).get(0).responseFalseList;
                //reponseVraie = db.QuestionDao().getQuestionAndResponseTrue(q.getLibelleQuestion()).responseTrue;
                //Log.d("Reponse vraie",reponseVraie.getLibelleReponseVraie());
            }
        });
        if(reponseFausseList == null)
            return false;
        else
            return true;
    }
    public void onQuestionRetrieved(Question q){

        question.setText(q.getLibelleQuestion());

    }
    public void onAnswersRetrieved(List<ReponseFausse> reponseFausseList) {

        if(!adapterAlreadySet)
        {
            adapter = new AnswerAdapter(reponseFausseList);
        rv = findViewById(R.id.answer_recycler);

        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        adapterAlreadySet= true;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.startQuiz) {
            start.setVisibility(View.GONE);
            jauge.setVisibility(View.VISIBLE);
            bar.setTitle(currentIndex+"/"+questionList.size());
            for(Question q: questionList) {
                while(true){
                    if(getCurrentAnswers(q))  // pour rendre l'exécution synchrone
                        break;
                }
                //getCurrentAnswers(q);
                onAnswersRetrieved(reponseFausseList);
                onQuestionRetrieved(q);
                countDownTimer = new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished)
                    {
                        jauge.setProgress((int) millisUntilFinished - 1000);
                    }
                    public void onFinish()
                    {
                        jauge.setProgress(10000);
                    }

                }.start();
                        currentIndex++;
            }
                }


        }

    }

