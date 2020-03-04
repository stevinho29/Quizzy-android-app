package com.example.quizzy.async;

import com.example.quizzy.pojo.IncomingJson;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("api.php?amount=10")
    Call<IncomingJson> getApiQuizz();
    @GET("api.php?amount=10&category=9")
    Call<IncomingJson> getGeneralKnwoledgeQuizz();
    @GET("api.php?amount=10&category=10")
    Call<IncomingJson> getBooksQuizz();
    @GET("api.php?amount=10&category=11")
    Call<IncomingJson> getFilmQuizz();
    @GET("api.php?amount=10&category=12")
    Call<IncomingJson> getMusicQuizz();
    @GET("api.php?amount=10&category=15")
    Call<IncomingJson> getVideoGamesQuizz();
    @GET("api.php?amount=10&category=17")
    Call<IncomingJson> getScienceNatureQuizz();
    @GET("api.php?amount=10&category=18")
    Call<IncomingJson> getComputerScienceQuizz();
    @GET("api.php?amount=10&category=19")
    Call<IncomingJson> getMathematicsQuizz();
    @GET("api.php?amount=10&category=21")
    Call<IncomingJson> getSportQuizz();
    @GET("api.php?amount=10&category=31")
    Call<IncomingJson> getAnimeQuizz();

}
