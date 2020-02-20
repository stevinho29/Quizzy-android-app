package com.example.quizzy.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizzy.R;
import com.example.quizzy.interfaces.QuestionChangeListener;
import com.example.quizzy.model.entities.Question;

import java.util.List;

public class QuestionsFragment extends Fragment implements QuestionChangeListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_quizzy,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onQuestionRetrieved(List<Question> questionList) {
        for(int  i=0; i < questionList.size();i++)
            Log.d("Questions:",questionList.get(i).getLibelleQuestion());
    }
}
