package com.example.quizzy.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.quizzy.model.entities.Party;
import com.example.quizzy.model.entities.PartyWithQuestions;
import com.example.quizzy.model.entities.PartyWithUsers;

import java.util.List;

@Dao
public interface PartyDao {

    @Query("SELECT * FROM Party")
    List<Party> getAllParties();

    @Transaction
    @Query("SELECT * FROM Party")
    List<PartyWithUsers> getPartyWithUsers();

    @Transaction
    @Query("SELECT * FROM Party")
    List<PartyWithQuestions> getPartyWithQuestions();

    @Insert
    void insertParty(Party party);
    @Update
    void updateParty(Party party);
    @Delete
    void deleteParty(Party party);
}
