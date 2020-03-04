package com.example.quizzy.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.quizzy.model.entities.Jouer;
import com.example.quizzy.model.entities.PartyWithUsers;
import com.example.quizzy.model.entities.UserWithParties;

import java.util.List;

@Dao
public interface JouerDao {

    @Query("SELECT * FROM Jouer ")
    List<Jouer> getAllPartiesWithUsers();

    @Transaction
    @Query("SELECT * FROM Jouer WHERE id_party=:id")
    PartyWithUsers getAPartyWithUsers(Integer id);

    @Transaction
    @Query("SELECT * FROM Jouer WHERE id_user=:id")
    UserWithParties getAUserWithParties(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPartyAndUser(Jouer party);
    @Update
    void updatePartyAndUser(Jouer party);
    @Delete
    void deletePartyAndUser(Jouer party);
}
