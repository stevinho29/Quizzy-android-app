package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;



public class Questions {

    @PrimaryKey()
    @NonNull
    private Integer id_question;
    @ColumnInfo
    private String libelleQuestion;

    @NonNull
    public Integer getId_question() {
        return id_question;
    }

    public void setId_question(@NonNull Integer id_question) {
        this.id_question = id_question;
    }

    public String getLibelleQuestion() {
        return libelleQuestion;
    }

    public void setLibelleQuestion(String libelleQuestion) {
        this.libelleQuestion = libelleQuestion;
    }
}
