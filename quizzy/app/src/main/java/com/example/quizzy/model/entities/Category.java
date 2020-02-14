package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Category {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id_category")
    private Integer id_category;
    @ColumnInfo(name = "libelleCategory")
    private String libelleCategory;

    public Category(Integer id, String libelle){
        this.id_category=id;
        this.libelleCategory= libelle;
    }
    public Category(){}
    @NonNull
    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(@NonNull Integer id_category) {
        this.id_category = id_category;
    }

    public String getLibelleCategory() {
        return libelleCategory;
    }

    public void setLibelleCategory(String libelle) {
        this.libelleCategory = libelle;
    }
}
