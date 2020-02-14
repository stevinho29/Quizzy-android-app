package com.example.quizzy.model.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class QuestionWithIndices {

    @Embedded
    public Question question;
    @Relation(
            parentColumn = "id_question",
            entityColumn = "questionsOwnerIndice_id"
    )
    public Indice indice;
}
