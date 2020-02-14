package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id_question")
    private Integer id_question;
    @ColumnInfo(name = "libelleQuestion")
    private String libelleQuestion;

    public Integer categoryOwnerQuestion_id;    // lien avec la clé primaire de Category

    public  Question(Integer id, String libelle, Integer id_category){
        this.id_question= id;
        this.libelleQuestion= libelle;
        this.categoryOwnerQuestion_id= id_category;
    }
    public Question(){}

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
