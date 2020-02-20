package com.example.quizzy.jobs;

import android.util.Log;

import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.Question;
import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.model.entities.ReponseVraie;
import com.example.quizzy.pojo.ApiPayload;
import com.example.quizzy.pojo.FromTriviaApi;


import java.util.ArrayList;


public class PersistData {
    private static  int i=0;
    private static  int j=0;

    public static void toDatabase(ArrayList<? extends ApiPayload> quizzList, UserDatabase db){

        ArrayList<FromTriviaApi> list= (ArrayList<FromTriviaApi>)quizzList;

        //db.clearAllTables();
        for(FromTriviaApi quizz: list){
            mayAddSomeQuestions(quizz,db);
        }
    }

    private static  void mayAddSomeQuestions(FromTriviaApi quizz, UserDatabase db){ // on ajoute des catégories, questions dans la base de donnée


        if(checkIfCategoryAlreadyExist(quizz.category,db)){
            System.out.println("JE PASSE EN HAUT");
            if(!checkIfQuestionAlreadyExist(quizz.question,db)){
                Integer id_c= db.CategoryDao().getCategoryBylibelle(quizz.category).getId_category();
                Log.d("ID_CATEG",id_c.toString());
                db.QuestionDao().insertQuestion(new Question(quizz.question,quizz.difficulty,id_c));
                Integer id_q= db.QuestionDao().getQuestionBylibelle(quizz.question).getId_question();

                db.ReponseVraieDao().insertAResponse(new ReponseVraie(quizz.correct_answers,id_q));

                for( String s: quizz.incorrect_answers){
                    db.ReponseFausseDao().insertAResponse(new ReponseFausse(s,id_q));

                }

            }
        }
        else{  // ici la catégorie n'existe pas encore de ce fait les questions et réponses aussi

            System.out.println("JE PASSE ICI");
            db.CategoryDao().insertCategory(new Category(quizz.category));
            Integer id_c= db.CategoryDao().getCategoryBylibelle(quizz.category).getId_category();

            db.QuestionDao().insertQuestion(new Question(quizz.question,quizz.difficulty,id_c));
            Integer id_q= db.QuestionDao().getQuestionBylibelle(quizz.question).getId_question();

            db.ReponseVraieDao().insertAResponse(new ReponseVraie(quizz.correct_answers,id_q));

            for( String s: quizz.incorrect_answers){
                db.ReponseFausseDao().insertAResponse(new ReponseFausse(s,id_q));

            }

        }


    }

    private static boolean checkIfCategoryAlreadyExist(String category,UserDatabase db){  // on check si la catégorie existe dans la BDD

        Category c= db.CategoryDao().checkIfCategory(category);
        if(c != null)
            return true;
        return false;
    }
    private static boolean checkIfQuestionAlreadyExist(String data,UserDatabase db){   // on check si la question existe dans la BDD

        Question q= db.QuestionDao().checkIfQuestion(data);
        if(q != null)
            return true;
        return false;
    }
}
