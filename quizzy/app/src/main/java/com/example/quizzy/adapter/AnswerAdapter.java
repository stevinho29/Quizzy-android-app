package com.example.quizzy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.R;
import com.example.quizzy.model.entities.ReponseFausse;


import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerHolder> {
    private List<ReponseFausse> mAnswers;
    private TextView libelle;
    public int mSelectedItem = -1;

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

        return new AnswerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerHolder answerHolder, int i) {
        answerHolder.mButton.setChecked(i == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return null != mAnswers ? mAnswers.size():0;
    }


    /*@Override
    public void onClick(View v) {
        mSelectedItem = getItem;
        libelle= v.findViewById(R.id.reponse);
        Log.d("answer listener",libelle.getText().toString());
    }*/

    public class AnswerHolder extends RecyclerView.ViewHolder  {
        public RadioButton mButton;

        public AnswerHolder(View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.selectAnswerButton);
            View.OnClickListener clickListener = v -> {
                mSelectedItem = getAdapterPosition();
                notifyDataSetChanged();
            };
            itemView.setOnClickListener(clickListener);
            mButton.setOnClickListener(clickListener);
        }
    }
}
