package com.example.quizzy.model.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class PartyWithUsers { // cette classe résulte de l'association many to many entre Uset et Party
  // cette classe permet de donner une partie la liste des utilisateurs

    @Embedded
    public Party party;
    @Relation(
            parentColumn = "id_party",
            entityColumn = "id_user",
            associateBy = @Junction(Jouer.class)
    )
    public List<User> userList;
}
