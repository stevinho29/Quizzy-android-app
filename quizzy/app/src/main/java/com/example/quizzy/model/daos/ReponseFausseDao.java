package com.example.quizzy.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.model.entities.ReponseVraie;

import java.util.List;

@Dao
public interface ReponseFausseDao {

    @Query("SELECT * FROM ReponseFausse ")
    List<ReponseFausse> getAllResponseFalse();

    @Query("SELECT * FROM ReponseFausse WHERE questionsOwnerResponseFalse_id= :id ")
    List<ReponseFausse> getAllResponseFalseForAQuestion(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAResponse(ReponseFausse response);
    @Update
    void updateAResponse(ReponseFausse response);
    @Delete
    void deleteAResponse(ReponseFausse response);
}
