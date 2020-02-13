package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Catégorie {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id_categorie")
    private Integer id_categorie;
    @ColumnInfo(name = "libelleCategorie")
    private String libelleCategorie;

    @NonNull
    public Integer getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(@NonNull Integer id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getLibelle() {
        return libelleCategorie;
    }

    public void setLibelle(String libelle) {
        this.libelleCategorie = libelle;
    }
}
