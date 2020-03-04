package com.example.quizzy.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.GuestActivity;
import com.example.quizzy.R;

import com.example.quizzy.interfaces.CategoryListener;
import com.example.quizzy.interfaces.SelectListener;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.view.CategoryViewHolder;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>  {

    private List<Category> mCategories;
    private TextView libelle;

    private CategoryListener mCategoryListener;

    //Constructeur
    public CategoryAdapter(List<Category> categories, CategoryListener categoryListener) {
        mCategories = categories;
        mCategoryListener = categoryListener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_category_item, parent, false);


        return new CategoryViewHolder(view, mCategoryListener);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int position) {
        categoryViewHolder.updateWithCategory(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return null != mCategories ? mCategories.size():0;
    }


}
