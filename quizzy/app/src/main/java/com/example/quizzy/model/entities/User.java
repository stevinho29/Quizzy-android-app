package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import java.util.Date;

@Entity(indices = {@Index(value = {"email","pseudo"}, unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_user")
    private Integer id_user;
    @ColumnInfo(name= "pseudo")
    private  String pseudo;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "surname")
    private String surname;
    @ColumnInfo(name = "birthDate")
    private Date birthDate;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;

    @Ignore
    public User(){
    }

    @NonNull
    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(@NonNull Integer id_user) {
        this.id_user = id_user;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User(String name,String surname,String email,String password,Date birthDate){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password= password;
        this.birthDate=birthDate;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

}
