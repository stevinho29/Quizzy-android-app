package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
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

import java.util.Collections;
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

    static String libelle = null;

    boolean running =   false;
    private int score = 0;

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
                libelle = extras.getString("libelle");

            }
        }
        if(libelle != null) {
            getQuestionForCurrentCategory(libelle);
        }else
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
        Log.d("running", running +"");
        if(!running) {
            reponseFausseList = null;
            reponseVraie = null;
            running = true;
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    reponseVraie = db.QuestionDao().getQuestionAndResponseTrue(q.getLibelleQuestion()).responseTrue;
                    reponseFausseList = db.QuestionDao().getQuestionAndResponseFalse(q.getLibelleQuestion()).get(0).responseFalseList;
                    reponseFausseList.add(reponseVraie.convertToReponseFausse());
                    Log.d("vraie", reponseVraie.getLibelleReponseVraie());
                }
            });
            return false;
        }
        else if(running && reponseFausseList == null) {
            return false;
        }
        else {
            running = false;
            Collections.shuffle(reponseFausseList);
            return true;
        }
    }
    public void onQuestionRetrieved(Question q){
        question.setText( Html.fromHtml(q.getLibelleQuestion(), HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
    public void onAnswersRetrieved(List<ReponseFausse> reponseFausseList) {

        if(!adapterAlreadySet)
        {
            adapter = new AnswerAdapter(reponseFausseList, reponseVraie);
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
            currentIndex = 1;
            new CountDownTimer(questionList.size()*10000, 10000) {
                public void onTick(long millisUntilFinished)
                {
                    if (adapter != null)
                        score += adapter.score;
                    bar.setTitle(currentIndex+"/" + questionList.size());
                    adapterAlreadySet = false;
                    Question q = questionList.get(currentIndex-1);//array commence à 0 index à 1
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
                public void onFinish()
                {
                        startActivity(getResultatIntent(score,libelle));
                }
            }.start();
            /*for(Question q: questionList) {
                bar.setTitle(currentIndex+"/"+questionList.size());
                adapterAlreadySet = false;
                while(true){
                    if(getCurrentAnswers(q)){  // pour rendre l'exécution synchrone
                        Log.d("msg", "1fois");
                        break;}
                }
                //getCurrentAnswers(q);
                onAnswersRetrieved(reponseFausseList);
                onQuestionRetrieved(q);
                /*countDownTimer = new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished)
                    {
                        jauge.setProgress((int) millisUntilFinished - 1000);
                        Log.d("msg", "timer");
                    }
                    public void onFinish()
                    {
                        jauge.setProgress(10000);
                    }

                }.start();
                currentIndex++;
            }*/
        }
    }

    private Intent getResultatIntent(int score,String libelle){
        final Intent resultatIntent = new Intent(this, ResultatActivity.class);
        final Bundle extras = new Bundle();
        extras.putString("libelle",libelle);
        extras.putString("score", score+"/"+questionList.size());
        resultatIntent.putExtras(extras);
        return resultatIntent;
    }
    /*@Override
    public void onBackPressed() {
        finish();
    }*/
}

