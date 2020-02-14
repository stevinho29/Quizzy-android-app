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
import com.example.quizzy.pojo.UserBasic;
import com.example.quizzy.pojo.UserLogInfo;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user ")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE name= :name ")
    List<User> getAUser(String name);

    @Query("SELECT name, surname FROM user WHERE email= :email")
    List<UserBasic> loadFullName(String email);

    @Query("SELECT email, password FROM user WHERE email= :email")
    List<UserLogInfo> loadLogInfo(String email);



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
