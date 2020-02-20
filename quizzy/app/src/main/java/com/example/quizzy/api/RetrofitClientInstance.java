package com.example.quizzy.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.quizzy.utils.Constants.OpenQuizzdb.URL_STREAM;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = URL_STREAM ;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
