package com.example.quizzy.model.daos;

import android.support.v4.app.INotificationSideChannel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.quizzy.model.entities.Question;
import com.example.quizzy.model.entities.QuestionAndReponseVraie;
import com.example.quizzy.model.entities.QuestionAndReponsesFalse;
import com.example.quizzy.model.entities.QuestionWithParties;
import com.example.quizzy.model.entities.User;

import java.util.List;

@Dao
public interface QuestionDao {

    @Transaction
    @Query("SELECT * FROM Question ")
    QuestionAndReponseVraie getQuestionAndResponseTrue();

    @Transaction
    @Query("SELECT * FROM Question ")
    List<QuestionAndReponsesFalse> getQuestionAndResponseFalse();

    @Transaction
    @Query("SELECT * FROM Question ")
    List<QuestionWithParties> getQuestionAndParties();

    @Query("SELECT * FROM Question ")
    List<Question> getAllQuestion();

    @Query("SELECT * FROM Question WHERE categoryOwnerQuestion_id= :id")
    List<Question> getSpecificQuestion(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestion(Question question);

    @Update
    void updateQuestion(Question question);
    @Delete
    void deleteQuestion(Question... questions);

}
