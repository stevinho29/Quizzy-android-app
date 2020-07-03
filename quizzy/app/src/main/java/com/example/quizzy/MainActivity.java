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
import com.example.quizzy.jobs.PersistData;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.Question;
import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.model.entities.User;
import com.example.quizzy.pojo.FromTriviaApi;
import com.example.quizzy.pojo.IncomingJson;
import com.example.quizzy.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.Arrays;
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

        PreferenceUtils.deletePrefs();


        ExecutorService executor = Executors.newSingleThreadExecutor();

        //RetrieveQuizzData quizz= new RetrieveQuizzData();
        //quizz.execute();

        /*Create handle for the RetrofitInstance interface
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
        });*/


        executor.submit(new Runnable() {
            @Override
            public void run() {
                ArrayList<FromTriviaApi> quizzList = new ArrayList<>();
                quizzList.add(new FromTriviaApi(
                                "Généralités",
                                "type",
                                "difficulté",
                                "L'air intérieur (dans les habitations) est-il plus ou moins pollué que l'air extérieur ?",
                                "Plus pollué",
                                new ArrayList<String>() {
                                    {
                                        add("Moins pollué");
                                        add("Identique");
                                        add("Identiques sous certaines conditions");
                                    }
                                }
                        )

                );
                quizzList.add(new FromTriviaApi(
                                "Généralités",
                                "type",
                                "difficulté",
                                "Que faire des restes de produits dangereux après usage ? (par ex.nreste de White spirit, nettoyant pour four, dissolvant..)",
                                "Les apporter à la déchetterie",
                                new ArrayList<String>() {
                                    {
                                        add("Les jeter dans les toilettes pour s'en débarrasser par les égouts");
                                        add("Les jeter sur le bord d'un chemin");
                                        add("Les jeter devant sa cour");
                                    }
                                }
                        )

                );
                quizzList.add(new FromTriviaApi(
                                "Généralités",
                                "type",
                                "difficulté",
                                "La diminution de la quantité de CO2 émise par les voitures est-elle une bonne solution pour lutter contre le réchauffement climatique ?",
                                "Oui et non",
                                new ArrayList<String>() {
                                    {
                                        add("Oui ");
                                        add("Non");
                                        add("Aucun impact");
                                    }
                                }
                        )

                );
                quizzList.add(new FromTriviaApi(
                                "Généralités",
                                "type",
                                "difficulté",
                                "Sur les emballages de certains cosmétiques, on trouve un pictogramme qui permet de connaître la période d'utilisation après ouverture (PAO). A quoi ressemble ce pictogramme ?",
                                "A un pot de crème ouvert",
                                new ArrayList<String>() {
                                    {
                                        add("A un point d'interrogation");
                                        add("A un tube de dentifrice ouvert");
                                        add("Il n’y a pas de pictogramme");
                                    }
                                }
                        )

                );
                quizzList.add(new FromTriviaApi(
                                "Généralités",
                                "type",
                                "difficulté",
                                "Une des conséquences prévisibles du réchauffement climatique est la montée du niveau des mers et des océans. Est-ce exact ?",
                                "Oui, car les glaces des continents vont fondre et les eaux des océans vont se dilater",
                                new ArrayList<String>() {
                                    {
                                        add("Non, avec les températures qui augmentent il y aura plus d'évaporation et donc moins d'eau");
                                        add("Aucun lien entre réchauffement et montée des eaux");
                                        add("Aucune des autres réponses");
                                    }
                                }
                        )

                );
                quizzList.add(new FromTriviaApi(
                                "Généralités",
                                "type",
                                "difficulté",
                                "Entre 1960 et 2000, la production annuelle d'ordures ménagères de chaque Burkinabè ...",
                                "a doublé",
                                new ArrayList<String>() {
                                    {
                                        add("a triplé");
                                        add("a quadruplé");
                                        add("est restée pareil");
                                    }
                                }
                        )

                );
                quizzList.add(new FromTriviaApi(
                                "Généralités",
                                "type",
                                "difficulté",
                                "Les publicités déposées dans nos boîtes aux lettres, chaque année, représentent une forte quantité de papier. Quelle est cette quantité ?",
                                "40kg",
                                new ArrayList<String>() {
                                    {
                                        add("33kg");
                                        add("22kg");
                                        add("47kg");
                                    }
                                }
                        )

                );
                quizzList.add(
                    new FromTriviaApi(
                        "Réchauffement climatique",
                        "type",
                        "difficulté",
                        "La principale raison pour laquelle le niveau des océans peut monter est ...",
                        "La banquise et les icebergs (par exemple autour du pôle Nord) vont fondre",
                        new ArrayList<String>() {
                            {
                                add("Il y aura plus de pluies donc plus d'eau dans les océans");
                                add("L'eau se dilate en se réchauffant");
                                add("On ne connaît pas les raisons");
                            }
                        }
                    )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Réchauffement climatique",
                                "type",
                                "difficulté",
                                "Le gaz à effet de serre que les humains relarguent le plus est ..",
                                "Les CFC",
                                new ArrayList<String>() {
                                    {
                                        add("Le dioxyde de carbone (CO2)");
                                        add("Le méthane (CH4)");
                                        add("L’ozone (O3)");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Réchauffement climatique",
                                "type",
                                "difficulté",
                                "La combustion de bois et de charbon produit du CO2. Qui du bois ou du charbon contribue le plus au réchauffement climatique ?",
                                "Le charbon",
                                new ArrayList<String>() {
                                    {
                                        add("Aucun des deux");
                                        add("Autant l'un que l'autre");
                                        add("Le bois");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Réchauffement climatique",
                                "type",
                                "difficulté",
                                "Quels pays émettent le plus de CO2 par habitant ?",
                                "Les pays riches",
                                new ArrayList<String>() {
                                    {
                                        add("Les pays pauvres");
                                        add("Il y a très peu de différences entre ces pays");
                                        add("On ne sait pas");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Réchauffement climatique",
                                "type",
                                "difficulté",
                                "Quelle est la cause première des émissions de CO2 ?",
                                "Les transports",
                                new ArrayList<String>() {
                                    {
                                        add("La culture du riz");
                                        add("Les bombes aérosols");
                                        add("Les décharges");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Réchauffement climatique",
                                "type",
                                "difficulté",
                                "Le plus gros problème de la déforestation d'un point de vue climatique est ...",
                                "La suppression d'arbres qui auraient consommé le CO2",
                                new ArrayList<String>() {
                                    {
                                        add("La consommation d'énergie lors de l'abattage");
                                        add("La déforestation n'a rien à voir avec l'effet de serre !");
                                        add("Les émissions lorsqu'on brûle la biomasse");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Réchauffement climatique",
                                "type",
                                "difficulté",
                                "Un véhicule mal réglé peut polluer :",
                                "Jusqu'à 50 fois plus qu'un véhicule bien réglé",
                                new ArrayList<String>() {
                                    {
                                        add("Jusqu'à 40 fois plus qu'un véhicule bien réglé");
                                        add("Jusqu'à 20 fois plus qu'un véhicule bien réglé");
                                        add("Jusqu'à 70 fois plus qu'un véhicule bien réglé");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Pollution",
                                "type",
                                "difficulté",
                                "Quel type de pollution n’existe pas ?",
                                "La pollution du corps",
                                new ArrayList<String>() {
                                    {
                                        add("La pollution de l’air");
                                        add("La pollution de l’eau ou de la nappe phréatique");
                                        add("Aucune des autres réponses");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Pollution",
                                "type",
                                "difficulté",
                                "Que signifie « bio » ?",
                                "En grec, « bio » signifie « la vie »",
                                new ArrayList<String>() {
                                    {
                                        add("En latin, « bio » signifie « la terre »");
                                        add("En grec, « bio » signifie « l’environnement »");
                                        add("En grec, « bio » signifie « feu »");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Pollution",
                                "type",
                                "difficulté",
                                "Les substances ci-dessous, normalement utilisées dans la fabrication de chaussures, sont toxiques et nocives pour l’homme et pour l’environnement. Toutes sauf une qui est utilisée dans la production de chaussures éco-compatibles. Laquelle ?",
                                "Les peaux tannées à partir d’extraits végétaux",
                                new ArrayList<String>() {
                                    {
                                        add("Le nickel");
                                        add("Les colorants azoïques");
                                        add("Le chrome VI");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Pollution",
                                "type",
                                "difficulté",
                                "Comment appelle-t-on les personnes qui se réunissent pour grouper leurs achats afin de s’adresser directement à des producteurs bio et écocompatibles ?",
                                "Des districts d’économie solidaire",
                                new ArrayList<String>() {
                                    {
                                        add("Des groupements d’achats");
                                        add("Des fabriques");
                                        add("Aucune des reponses precedantes");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Pollution",
                                "type",
                                "difficulté",
                                "Comment appelle-t-on le fait de donner une « seconde » vie à des déchets ?",
                                "Le recyclage",
                                new ArrayList<String>() {
                                    {
                                        add("La décharge");
                                        add("La déchetterie");
                                        add("La réincarnation");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Pollution",
                                "type",
                                "difficulté",
                                "Quel nom donne-t-on au phénomène thermique où l’atmosphère laisse passer une partie du rayonnement du soleil qui vient chauffer le sol ?",
                                "L’effet de serre",
                                new ArrayList<String>() {
                                    {
                                        add("La pollution ");
                                        add("Le gaz toxique ");
                                        add("Le rayonnement");
                                    }
                                }
                        )
                );
                quizzList.add(
                        new FromTriviaApi(
                                "Pollution",
                                "type",
                                "difficulté",
                                "Quel est le contraire de l’énergie renouvelable ?",
                                "L’énergie épuisable",
                                new ArrayList<String>() {
                                    {
                                        add("L’énergie photovoltaïque");
                                        add("L’énergie éolienne");
                                        add("L’énergie thermique");
                                    }
                                }
                        )
                );
                /*
                quizzList.add(
                    new FromTriviaApi(
                        "category",
                        "type",
                        "difficulté",
                        "question",
                        "corr",
                        new ArrayList<String>() {
                            {
                                add("A");
                                add("B");
                                add("C");
                            }
                        }
                    )
                );*/
                PersistData.quizzInfoToDatabase(quizzList);
                List<Category> listCategory = db.CategoryDao().getAllCategory();
                for(Category c: listCategory){
                    Log.d("Quizz Category", c.getLibelleCategory());
                    Log.d("Quizz Category ID ", c.getId_category().toString());
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
