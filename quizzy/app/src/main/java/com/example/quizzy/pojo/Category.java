package com.example.quizzy.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Category extends ApiPayload {

    @SerializedName("category")
    public String category;

    @SerializedName("type")
    public String type;

    @SerializedName("difficulty")
    public String difficulty;

    @SerializedName("question")
    public String question;

    @SerializedName("correct_answers")
    public String correct_answers;

    @SerializedName("incorrect_answers")
    public ArrayList<String> incorrect_answers;

    public Category(String category, String type, String difficulty, String question, String correct_answers, ArrayList<String> incorrect_answers){
        this.category= category;
        this.type= type;
        this.difficulty= difficulty;
        this.question=question;
        this.correct_answers= correct_answers;
        this.incorrect_answers= incorrect_answers;
    }
    @Override
    public  String toString(){

        return "category: "+this.category+" type: "+this.type+" difficulty: "+this.difficulty+" question: "+this.question
                +" correct_answers: "+this.correct_answers;
    }
}
