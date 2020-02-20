package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ReponseVraie {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_reponseVraie")
    private Integer id_reponseVraie;
    @ColumnInfo(name = "libelleReponseVraie")
    private String libelleReponseVraie;

    public Integer questionsOwnerResponseTrue_id;   // lien vers la clé primaire de la table reponse vraie

    public ReponseVraie( String libelleReponseVraie,Integer questionsOwnerResponseTrue_id){

        this.libelleReponseVraie= libelleReponseVraie;
        this.questionsOwnerResponseTrue_id= questionsOwnerResponseTrue_id;
    }
    @Ignore
    public ReponseVraie(){}
    @NonNull
    public Integer getId_reponseVraie() {
        return id_reponseVraie;
    }

    public void setId_reponseVraie(@NonNull Integer id_reponseVraie) {
        this.id_reponseVraie = id_reponseVraie;
    }

    public String getLibelleReponseVraie() {
        return libelleReponseVraie;
    }

    public void setLibelleReponseVraie(String libelleReponseVraie) {
        this.libelleReponseVraie = libelleReponseVraie;
    }
}
