package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Partie {

    @PrimaryKey()
    @NonNull
    private Integer id_partie;
    @ColumnInfo
    private Integer duree;
    @ColumnInfo
    private Integer score;

    @NonNull
    public Integer getId_partie() {
        return id_partie;
    }

    public void setId_partie(@NonNull Integer id_partie) {
        this.id_partie = id_partie;
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
}
