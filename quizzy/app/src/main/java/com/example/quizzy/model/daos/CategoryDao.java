package com.example.quizzy.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.CategoryAndQuestions;
import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM Category")
    List<Category> getAllCategory();

    @Transaction
    @Query("SELECT * FROM Category ")
    List<CategoryAndQuestions> getCategoryAndQuestions();


    @Query("SELECT * FROM category WHERE id_category= :id")
    Category getCategoryById(Integer id);

    @Query("SELECT * FROM category WHERE libelleCategory= :libelle")
    Category getCategoryBylibelle(String libelle);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);
    @Update
    void updateCategory(Category category);
    @Delete
    void deleteCategory(Category category);

}
