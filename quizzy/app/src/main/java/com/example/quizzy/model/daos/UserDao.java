package com.example.quizzy.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.quizzy.model.entities.User;
import com.example.quizzy.model.entities.UserWithParties;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user ")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE name= :name ")
    List<User> getAUser(String name);

    @Query("SELECT * FROM User where email= :email and password= :password ")
    User getAUser(String email, String password);

    @Query(("SELECT * FROM user Where pseudo=:pseudo AND password= :password"))
    User getAutehenticatedUser(String pseudo, String password);
    @Transaction
    @Query("SELECT * FROM User")
    List<UserWithParties> getUserWithParties();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Update
    void updateUser(User user);
    @Delete
    void deleteUsers(User... users);
}
