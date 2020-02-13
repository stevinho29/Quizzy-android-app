package com.example.quizzy.model.Repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.quizzy.model.converters.Converters;
import com.example.quizzy.model.daos.UserDao;
import com.example.quizzy.model.entities.User;


@Database(entities = {User.class},version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class UserDatabase extends RoomDatabase{

        //elle permet de définir quelles sont entités et quels sont nos DAO
        public abstract UserDao UserDao();
    }


