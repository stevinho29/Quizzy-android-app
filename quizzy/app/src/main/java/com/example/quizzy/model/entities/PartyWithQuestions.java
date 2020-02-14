package com.example.quizzy.model.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class PartyWithQuestions {//cette classe résulte de la relation many to many entre Question et Partie
    //elle permet de retrouver toutes les questions qui ont été posées lors d'une partie bien précise

    @Embedded
    public Party party;
    @Relation(
            parentColumn = "id_party",
            entityColumn = "id_question",
            associateBy = @Junction(HavingQuestions.class)
    )
    public List<Question> questionList;
}
