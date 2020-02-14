package com.example.quizzy.model.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UserWithParties {  // cette classe résulte de l'association many to many entre Uset et Party
    // cette classe permet de donner un utilisateur et les différentes parties qu'il a eu à faire
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id_user",
            entityColumn = "id_party",
            associateBy = @Junction(Jouer.class)
    )
    public List<Party> partyList;
}
