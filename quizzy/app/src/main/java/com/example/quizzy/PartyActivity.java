package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzy.adapter.AnswerAdapter;
import com.example.quizzy.adapter.CategoryAdapter;
import com.example.quizzy.interfaces.SelectListener;
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

import java.util.Collection;
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

    private static MediaPlayer mediaPlayer;
    private Button start;
    private ProgressBar jauge;
    static long globalMillis;
    static long millis;
    private TextView question;

    private static CountDownTimer globalCountDownTimer=null;
    private static CountDownTimer singleCountDownTimer= null;
    private boolean adapterAlreadySet= false;
    private Toolbar bar;
    private Integer currentIndex =1;
    boolean canceled = false;

    AnswerAdapter adapter;
    RecyclerView rv;
    SelectListener mSelectedResponse= new SelectListener() {
        @Override
        public void onResponseSelected() {
            if(singleCountDownTimer != null) {
                canceled = true;
            }
        }
    };
    static String libelle = null;

    boolean running =   false;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        db= QuizzyApplication.getDb();

        if(mediaPlayer != null)
            mediaPlayer.release();

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

                Collections.shuffle(questionList);      // on mélange les questions pour ne pas avoir à chaque fois les mm et dans le mm ordre
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
            adapter = new AnswerAdapter(reponseFausseList, reponseVraie,mSelectedResponse);
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

            if( questionList.size() >=10) {
                currentIndex = 1;
                onStartQuizzMusic();
                globalCountDownTimer = new CountDownTimer(10 * 10000, 10000) {
                    public void onTick(long globalMillis) {

                        if (adapter != null)
                            score += adapter.score;
                        bar.setTitle(currentIndex + "/" + 10);
                        adapterAlreadySet = false;
                        Question q = questionList.get(currentIndex - 1);//array commence à 0 index à 1
                        while (true) {
                            if (getCurrentAnswers(q))  // pour rendre l'exécution synchrone
                                break;
                        }
                        //getCurrentAnswers(q);
                        onAnswersRetrieved(reponseFausseList);
                        onQuestionRetrieved(q);
                        singleCountDownTimer = new CountDownTimer(10000, 1000) {
                            public void onTick(long millis) {
                                jauge.setProgress((int) millis - 1000);
                            }

                            public void onFinish() {
                                jauge.setProgress(10000);
                            }
                        }.start();
                        currentIndex++;
                    }

                    public void onFinish() {
                        releaseMediaPlayer();
                        startActivity(getResultatIntent(score, libelle));
                        finish();
                    }
                }.start();
            }else{
                Toast.makeText(PartyActivity.this,"There is not enough questions for this theme... please wait until we provide enough",Toast.LENGTH_SHORT).show();
            }

            /*
            for(int i=0;i < 10 ; i++){
                globalCountDownTimer= new CountDownTimer(10000,10000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        onStartQuizzMusic();
                        if (adapter != null)
                            score += adapter.score;
                        bar.setTitle(currentIndex+"/" + 10);
                        adapterAlreadySet = false;
                        Question q = questionList.get(currentIndex-1);//array commence à 0 index à 1
                        Log.d("bjkhb", q.getLibelleQuestion());
                        while(true){
                            if(getCurrentAnswers(q))  // pour rendre l'exécution synchrone
                                break;
                        }
                        //getCurrentAnswers(q);
                        onAnswersRetrieved(reponseFausseList);
                        onQuestionRetrieved(q);
                        singleCountDownTimer = new CountDownTimer(10000, 1000) {
                            public void onTick(long millis)
                            {
                                if(!canceled)
                                    jauge.setProgress((int) millis - 1000);
                                else{
                                    mediaPlayer.stop();
                                    cancel();
                                    globalCountDownTimer.cancel();
                                    jauge.setProgress(10000);
                                }
                            }
                            public void onFinish()
                            {
                                mediaPlayer.stop();
                                jauge.setProgress(10000);
                            }
                        }.start();
                        currentIndex++;
                    }

                    @Override
                    public void onFinish() { }
                };
            }
            */

        }
    }

    private Intent getResultatIntent(int score,String libelle){
        final Intent resultatIntent = new Intent(this, ResultatActivity.class);
        final Bundle extras = new Bundle();
        extras.putString("libelle",libelle);
        extras.putString("score", score+"/10");
        resultatIntent.putExtras(extras);
        return resultatIntent;
    }

    public void onStartQuizzMusic(){
        int jinglegoquizzId= getResources().getIdentifier("jinglegoquizz","raw",getPackageName());
        mediaPlayer= MediaPlayer.create(PartyActivity.this,jinglegoquizzId);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }
    public static void releaseMediaPlayer(){
        if( mediaPlayer != null) {
            if(mediaPlayer.isLooping()) {
                mediaPlayer.setLooping(false);
                if(mediaPlayer.isPlaying())
                    mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }
    @Override
    public void onBackPressed() {
    super.onBackPressed();
    if(singleCountDownTimer != null)
        singleCountDownTimer.cancel();
    if(globalCountDownTimer != null)
        globalCountDownTimer.cancel();
    releaseMediaPlayer();
    finish();
    }

}

