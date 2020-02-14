package com.example.quizzy.model.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class QuestionAndReponseVraie {  // cette classe résulte de la erlation one to one entre Question et ReponseVraie

    @Embedded
    public Question question;
    @Relation(
            parentColumn = "id_question",
            entityColumn = "questionsOwnerResponseTrue_id"
    )
    public ReponseVraie responseTrue;
}
