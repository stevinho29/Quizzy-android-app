package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"id_question", "id_party"})
public class HavingQuestions { // la table HavingQuestion se décline de la relation many to many (MCD -> MLD)
    @NonNull
    public Integer id_question;
    @NonNull
    public Integer id_party;

}
