package com.example.quizzy.model.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.quizzy.model.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE name= :name")
    List<User> getAllUser(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
}
