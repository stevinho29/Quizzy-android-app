package com.example.quizzy.jobs;

import android.util.Log;
import android.widget.Toast;

import com.example.quizzy.MainActivity;
import com.example.quizzy.api.RetrofitClientInstance;
import com.example.quizzy.async.GetDataService;
import com.example.quizzy.pojo.IncomingJson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class FirstLoadFromApi {

    private static GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    public FirstLoadFromApi() {
        task();
    }


    private void task() {
        Call<IncomingJson> call = service.getBooksQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for books", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });

            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on booksQuizz ", "didn't get a shit from API");

            }
        });

        call= service.getGeneralKnwoledgeQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for knowledge", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
        call= service.getAnimeQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for Anime quizz", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
        call= service.getComputerScienceQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for Computer science quizz", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
        call= service.getFilmQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for Film quizz", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
        call= service.getMathematicsQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for Mathematics", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
        call= service.getSportQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for Sport quizz", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
        call= service.getVideoGamesQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for video games quizz", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
        call= service.getMusicQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for Music", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
        call= service.getScienceNatureQuizz();
        call.enqueue(new Callback<IncomingJson>() {
            @Override
            public void onResponse(Call<IncomingJson> call, Response<IncomingJson> response) {
                Log.d("Retrofit response for Science et nature", response.body().results.toString());
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        PersistData.quizzInfoToDatabase(response.body().results);
                    }
                });
            }
            @Override
            public void onFailure(Call<IncomingJson> call, Throwable t) {
                Log.d("Retrofit ERROR on knowledge quizz", "didn't get a shit from API");
            }
        });
    }
}