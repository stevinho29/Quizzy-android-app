package com.example.quizzy.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.quizzy.api.LinkToApi;


public class RetrieveQuizzData extends AsyncTask<Void, Void, String> {  // will be delete soon but don't touch

    public  RetrieveQuizzData(){}

    @Override
    protected void onPreExecute() {
        //progressBar.setVisibility(View.VISIBLE);
        //responseView.setText("");
    }
    @Override
    protected String doInBackground(Void... urls) {
        return LinkToApi.fetchDataFromApi();
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
       // progressBar.setVisibility(View.GONE);
        Log.d("FROM api", response);
        //responseView.setText(response);
        // TODO: check this.exception
        // TODO: do something with the feed

    }
}
