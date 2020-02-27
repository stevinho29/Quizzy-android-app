package com.example.quizzy.jobs;

import android.util.Log;

import com.example.quizzy.QuizzyApplication;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.Question;
import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.model.entities.ReponseVraie;
import com.example.quizzy.model.entities.User;
import com.example.quizzy.pojo.ApiPayload;
import com.example.quizzy.pojo.FromTriviaApi;


import java.sql.SQLException;
import java.util.ArrayList;


public class PersistData {

     static UserDatabase db= QuizzyApplication.getDb();

    public static void quizzInfoToDatabase(ArrayList<? extends ApiPayload> quizzList){

        ArrayList<FromTriviaApi> list= (ArrayList<FromTriviaApi>)quizzList;

        //db.clearAllTables();
        for(FromTriviaApi quizz: list){
            mayAddSomeQuestions(quizz);
        }
    }

    private static  void mayAddSomeQuestions(FromTriviaApi quizz){ // on ajoute des catégories, questions dans la base de donnée


        if(checkIfCategoryAlreadyExist(quizz.category)){
            System.out.println("JE PASSE EN HAUT");
            if(!checkIfQuestionAlreadyExist(quizz.question)){
                Category category = db.CategoryDao().getCategoryBylibelle(quizz.category);
                Integer id_c = category.getId_category();

                db.QuestionDao().insertQuestion(new Question(quizz.question,quizz.difficulty,id_c));
                Question question= db.QuestionDao().getQuestionBylibelle(quizz.question);
                Integer id_q= question.getId_question();
                db.ReponseVraieDao().insertAResponse(new ReponseVraie(quizz.correct_answer,id_q));

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

            db.ReponseVraieDao().insertAResponse(new ReponseVraie(quizz.correct_answer,id_q));

            for( String s: quizz.incorrect_answers){
                db.ReponseFausseDao().insertAResponse(new ReponseFausse(s,id_q));

            }

        }


    }

    public static void userInfoTodatabase(User user)  {
            db.UserDao().insertUser(user);

    }

    public  static boolean authentication(String pseudo, String password){
        User user= db.UserDao().getAutehenticatedUser(pseudo,password);
        if(user != null)
            return true;
        else
            return false;
    }

    private static boolean checkIfCategoryAlreadyExist(String category){  // on check si la catégorie existe dans la BDD

        Category c= db.CategoryDao().getCategoryBylibelle(category);
        if(c != null)
            return true;
        return false;
    }
    private static boolean checkIfQuestionAlreadyExist(String data){   // on check si la question existe dans la BDD

        Question q= db.QuestionDao().getQuestionBylibelle(data);
        if(q != null)
            return true;
        return false;
    }
}
