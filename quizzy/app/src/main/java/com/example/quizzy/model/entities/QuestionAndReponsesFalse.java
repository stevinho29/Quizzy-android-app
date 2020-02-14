package com.example.quizzy.model.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class QuestionAndReponsesFalse {  // cette classe résulte de l'association many to one entre Question et ReponseFausse
    @Embedded
    public Question question;
    @Relation(
            parentColumn = "id_question",
            entityColumn = "questionsOwnerResponseFalse_id"
    )
    public List<ReponseFausse> responseFalseList;
}
