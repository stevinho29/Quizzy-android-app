package com.example.quizzy.api;


import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.quizzy.utils.Constants.OpenQuizzdb.URL_STREAM;

public class LinkToApi {

    public static String fetchDataFromApi(){

        try {
            URL url = new URL(URL_STREAM);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                connection.disconnect();
            }
        }
        catch(Exception e) {
            Log.d("ERROR while fetcHING from api", e.getMessage(), e);
            return null;
        }
    }

}
