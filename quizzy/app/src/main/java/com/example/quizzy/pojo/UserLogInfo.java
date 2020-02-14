package com.example.quizzy.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class UserLogInfo {  // user log information when attempting to log in


        @ColumnInfo(name = "email")
        public String email;

        @ColumnInfo(name = "password")
        @NonNull
        public String password;

}
