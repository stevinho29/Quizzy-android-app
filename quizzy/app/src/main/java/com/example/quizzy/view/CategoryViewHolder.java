package com.example.quizzy.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.R;
import com.example.quizzy.interfaces.CategoryListener;
import com.example.quizzy.model.entities.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public static int numero = 0;
    public CardView cardView;
    public TextView headTextView;
    public TextView footTextView;
    public CategoryListener categoryListener;

    public CategoryViewHolder(View itemView, CategoryListener categoryListener) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardH);
        headTextView = itemView.findViewById(R.id.headCard);
        footTextView = itemView.findViewById(R.id.footCard);
        this.categoryListener = categoryListener;
        itemView.setOnClickListener(this);
    }

    public void updateWithCategory(Category category){
        this.headTextView.setText(category.getLibelleCategory());
        this.footTextView.setText("Testez vos connaissances en " + category.getLibelleCategory());
        //régler la couleur
        numero++;
        switch (numero){
            case(1): cardView.setCardBackgroundColor(0xffFBC200);
                break;
            case(2): cardView.setCardBackgroundColor(0xffFF0065);
                break;
            default:
                cardView.setCardBackgroundColor(0xff2B0050);
                numero = 0;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        TextView libelle = v.findViewById(R.id.headCard);
        categoryListener.onViewCategory(libelle.getText().toString());
    }
}
