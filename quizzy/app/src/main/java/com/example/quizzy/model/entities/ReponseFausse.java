package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ReponseFausse {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id_reponseFausse")
    private Integer id_reponseFausse;
    @ColumnInfo(name = "libelleReponseFausse")
    private String libelleReponseFausse;

    @NonNull
    public Integer getId_reponseFausse() {
        return id_reponseFausse;
    }

    public void setId_reponseFausse(@NonNull Integer id_reponseFausse) {
        this.id_reponseFausse = id_reponseFausse;
    }

    public String getLibelleReponseFausse() {
        return libelleReponseFausse;
    }

    public void setLibelleReponseFausse(String libelleReponseFausse) {
        this.libelleReponseFausse = libelleReponseFausse;
    }
}
