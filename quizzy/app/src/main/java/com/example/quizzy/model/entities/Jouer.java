package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"id_user", "id_party"})
public class Jouer {   // la table jouer se décline de la relation many to many (MCD -> MLD)

    @NonNull
    public Integer id_user;
    @NonNull
    public Integer id_party;
}
