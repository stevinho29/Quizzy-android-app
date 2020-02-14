package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Indice {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id_indice")
    private Integer id_indice;
    @ColumnInfo(name = "libelleIndice")
    private String libelleIndice;

    public Integer questionsOwnerIndice_id; // lien vers la clé primaire question

    @NonNull
    public Integer getId_indice() {
        return id_indice;
    }

    public void setId_indice(@NonNull Integer id_indice) {
        this.id_indice = id_indice;
    }

    public String getLibelleIndice() {
        return libelleIndice;
    }

    public void setLibelleIndice(String libelle) {
        this.libelleIndice = libelle;
    }
}
