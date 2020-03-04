package com.example.quizzy.model.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithParties { //cette classe résulte de la relation many to many entre Question et Partie
    //elle permet de retrouver toutes les parties pour lesquelles une question a été posée (pas trè!s utile pour le moment à voir)

    @Embedded
    public Category category;
    @Relation(
            parentColumn = "id_category",
            entityColumn = "categoryOwnerParty_id"
    )
    public List<Party> partyList;
}
