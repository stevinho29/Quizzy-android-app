package com.example.quizzy.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FromTriviaApi extends ApiPayload {

    @SerializedName("category")
    public String category;

    @SerializedName("type")
    public String type;

    @SerializedName("difficulty")
    public String difficulty;

    @SerializedName("question")
    public String question;

    @SerializedName("correct_answer")
    public String correct_answer;

    @SerializedName("incorrect_answers")
    public ArrayList<String> incorrect_answers;

    public FromTriviaApi(String category, String type, String difficulty, String question, String correct_answer, ArrayList<String> incorrect_answers){
        this.category= category;
        this.type= type;
        this.difficulty= difficulty;
        this.question=question;
        this.correct_answer= correct_answer;
        this.incorrect_answers= incorrect_answers;
    }
    @Override
    public  String toString(){

        return "category: "+this.category+" type: "+this.type+" difficulty: "+this.difficulty+" question: "+this.question
                +" correct_answer: "+this.correct_answer+"incorrect_answers : "+this.incorrect_answers.toString();
    }
}
