package com.example.quizzy.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quizzy.model.entities.ReponseVraie;

import java.util.List;

@Dao
public interface ReponseVraieDao {

    @Query("SELECT * FROM ReponseVraie ")
    List<ReponseVraie> getAllResponseTrue();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAResponse(ReponseVraie response);
    @Update
    void updateAResponse(ReponseVraie response);
    @Delete
    void deleteAResponse(ReponseVraie response);

}
