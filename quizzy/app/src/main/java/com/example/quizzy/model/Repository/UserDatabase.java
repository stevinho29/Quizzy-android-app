package com.example.quizzy.model.Repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.quizzy.model.converters.Converters;
import com.example.quizzy.model.daos.CategoryDao;
import com.example.quizzy.model.daos.IndiceDao;
import com.example.quizzy.model.daos.PartyDao;
import com.example.quizzy.model.daos.QuestionDao;
import com.example.quizzy.model.daos.ReponseFausseDao;
import com.example.quizzy.model.daos.ReponseVraieDao;
import com.example.quizzy.model.daos.UserDao;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.model.entities.HavingQuestions;
import com.example.quizzy.model.entities.Indice;
import com.example.quizzy.model.entities.Jouer;
import com.example.quizzy.model.entities.Party;
import com.example.quizzy.model.entities.Question;
import com.example.quizzy.model.entities.ReponseFausse;
import com.example.quizzy.model.entities.ReponseVraie;
import com.example.quizzy.model.entities.User;


@Database(entities = {User.class, ReponseVraie.class, ReponseFausse.class, Question.class, Jouer.class, Party.class, Category.class, HavingQuestions.class, Indice.class},version =1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class UserDatabase extends RoomDatabase{

        //elle permet de définir quelles sont entités et quels sont nos DAO
        public abstract UserDao UserDao();

        public abstract ReponseVraieDao ReponseVraieDao();

        public abstract ReponseFausseDao ReponseFausseDao();

        public abstract QuestionDao QuestionDao();

        public abstract PartyDao PartyDao();

        public abstract CategoryDao CategoryDao();

        public abstract IndiceDao IndiceDao();

    }


