package com.example.quizzy.view;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.R;

public class AnswerHolder extends RecyclerView.ViewHolder  {
    public TextView reponseView;

    public AnswerHolder(View itemView) {
        super(itemView);
        reponseView = itemView.findViewById(R.id.reponse);
    }
}
