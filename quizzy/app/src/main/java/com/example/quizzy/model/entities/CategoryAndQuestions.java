package com.example.quizzy.model.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryAndQuestions { //cette classe résulte de la relation many to one entre Category et Question

    @Embedded
    public Category category;
    @Relation(
            parentColumn = "id_category",
            entityColumn = "categoryOwnerQuestion_id"
    )
    public List<Question> questionList;
}
