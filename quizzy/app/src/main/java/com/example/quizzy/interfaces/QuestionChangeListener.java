package com.example.quizzy.interfaces;

import com.example.quizzy.model.entities.Question;

import java.util.List;

public interface QuestionChangeListener {
    public void onQuestionRetrieved(List<Question> questionList);
}
