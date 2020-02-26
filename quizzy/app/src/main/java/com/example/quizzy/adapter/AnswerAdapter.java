package com.example.quizzy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.R;
import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.view.AnswerHolder;


import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerHolder> implements View.OnClickListener {
    private List<ReponseFausse> mAnswers;
    private TextView libelle;
    private Button selectedButton;

    public AnswerAdapter(List<ReponseFausse> list) {
        mAnswers = list;
    }
    @NonNull
    @Override
    public AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_answer_item, parent, false);

        view.setOnClickListener(this);

        return new AnswerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerHolder answerHolder, int i) {
        answerHolder.updateAnswer(mAnswers.get(i));
    }

    @Override
    public int getItemCount() {
        return null != mAnswers ? mAnswers.size():0;
    }


    @Override
    public void onClick(View v) {
        libelle= v.findViewById(R.id.reponse);
        Log.d("answer listener",libelle.getText().toString());
    }
}
