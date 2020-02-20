package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class Party {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_party")
    private Integer id_party;
    @ColumnInfo(name = "duree")
    private Integer duree;
    @ColumnInfo(name = "score")
    private Integer score;
    @ColumnInfo(name = "date_party")
    public Date date_party;

    public Party( Integer duree, Integer score, Date date){

        this.duree= duree;
        this.score= score;
        this.date_party= date;
    }
    public Party(){}
    @NonNull
    public Integer getId_party() {
        return id_party;
    }

    public void setId_party(@NonNull Integer id_party) {
        this.id_party = id_party;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDate_party() { return date_party; }

    public void setDate_party(Date date_party) { this.date_party = date_party; }
}
