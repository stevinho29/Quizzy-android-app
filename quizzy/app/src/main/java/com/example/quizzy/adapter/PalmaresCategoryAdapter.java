package com.example.quizzy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.R;
import com.example.quizzy.interfaces.CategoryListener;
import com.example.quizzy.interfaces.SelectListener;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.view.PalmaresCategoryViewHolder;

import java.util.List;

public class PalmaresCategoryAdapter extends RecyclerView.Adapter<PalmaresCategoryViewHolder> implements View.OnClickListener {

    private List<Category> mCategories;
    private TextView libelle;
    private SelectListener mListener;
    private CategoryListener mCategoryListener;

    //Constructeur
    public PalmaresCategoryAdapter(List<Category> categories, CategoryListener categoryListener) {
        mCategories = categories;
        mCategoryListener = categoryListener;
    }

    @Override
    public PalmaresCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.palmares_category, parent, false);

        view.setOnClickListener(this);

        return new PalmaresCategoryViewHolder(view, mCategoryListener);
    }

    @Override
    public void onBindViewHolder(PalmaresCategoryViewHolder holder, int position) {
        holder.updateWithCategory(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return null != mCategories ? mCategories.size():0;
    }

    public void setCategoryListener(SelectListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {

    }
}
