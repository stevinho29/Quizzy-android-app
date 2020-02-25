package com.example.quizzy.interfaces;

import com.example.quizzy.model.entities.Category;

import java.util.List;

public interface CategoryChangeListener {
    public void onCategoryRetrieved(List<Category> categoryList);
}
