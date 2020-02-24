package com.example.quizzy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.HomeApplication;
import com.example.quizzy.R;
import com.example.quizzy.pojo.Category;
import com.example.quizzy.view.CategoryViewHolder;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> mCategories;
    //private final LayoutInflater mInflater;

    //Constructeur
    public CategoryAdapter(List<Category> categories) {
        mCategories = categories;
        //mInflater = LayoutInflater.from(HomeApplication.getContext());
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_item, parent, false);

        return new CategoryViewHolder(view);
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
