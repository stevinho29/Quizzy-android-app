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
}
