package com.example.quizzy.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.R;
import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.model.entities.ReponseVraie;


import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerHolder> {
    private List<ReponseFausse> mAnswers;
    private ReponseVraie mTrue;
    private TextView libelle;
    public int mSelectedItem = -1;
    public int score = 0;

    public AnswerAdapter(List<ReponseFausse> list, ReponseVraie rVraie) {
        mAnswers = list;
        mTrue = rVraie;
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
        answerHolder.updateAnswer(mAnswers.get(i));
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
        public TextView mLabel;

        public AnswerHolder(View itemView) {
            super(itemView);
            mLabel = itemView.findViewById(R.id.reponse);
            mButton = itemView.findViewById(R.id.selectAnswerButton);
            View.OnClickListener clickListener = v -> {
                mSelectedItem = getAdapterPosition();
                if(mLabel.getText().toString().equals(mTrue.getLibelleReponseVraie()))
                    score = 1;
                else
                    score = 0;
                notifyDataSetChanged();
            };
            itemView.setOnClickListener(clickListener);
            mButton.setOnClickListener(clickListener);
        }
        public  void updateAnswer(ReponseFausse reponseFausse){
            mLabel.setText( Html.fromHtml(reponseFausse.getLibelleReponseFausse(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        }
    }
}
