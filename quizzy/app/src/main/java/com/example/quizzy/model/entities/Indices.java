package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Indices {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id_indice")
    private Integer id_indice;
    @ColumnInfo(name = "libelleIndice")
    private String libelleIndice;

    @NonNull
    public Integer getId_indice() {
        return id_indice;
    }

    public void setId_indice(@NonNull Integer id_indice) {
        this.id_indice = id_indice;
    }

    public String getLibelle() {
        return libelleIndice;
    }

    public void setLibelle(String libelle) {
        this.libelleIndice = libelle;
    }
}
