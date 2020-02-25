package com.example.quizzy.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.R;
import com.example.quizzy.pojo.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView headTextView;
    public TextView footTextView;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardH);
        headTextView = itemView.findViewById(R.id.headCard);
        footTextView = itemView.findViewById(R.id.footCard);
    }

    public void updateWithCategory(Category category){
        this.headTextView.setText(category.category);
        this.footTextView.setText("@string/foot_text_" + category.category);
        //ajouter après @string/category cad @string/geographie etc... dans les ressources
        //régler la couleur
    }
}
