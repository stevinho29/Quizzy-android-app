package com.example.quizzy.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class UserBasic {  // user basic information returned from database

        @ColumnInfo(name = "name")
        public String name;

        @ColumnInfo(name = "surname")
        @NonNull
        public String surname;

}
