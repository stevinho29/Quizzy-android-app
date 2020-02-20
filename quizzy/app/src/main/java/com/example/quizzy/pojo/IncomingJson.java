package com.example.quizzy.pojo;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class IncomingJson {
    @SerializedName("response_code")
    public Integer response_code;

    @SerializedName("results")
    public ArrayList<FromTriviaApi> results;


    @Override
    public String toString() {

        String tostr="";
        for( int i= 0; i < this.results.size(); i++)
            tostr= tostr.concat(results.get(i).toString());
        return tostr;
    }


}
