package com.example.quizzy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.quizzy.MainActivity;
import com.example.quizzy.QuizzyApplication;

public class PreferenceUtils {
    @SuppressWarnings("unused")
    private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSharedPreferences(){
        return QuizzyApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static String getUsername(){
        final SharedPreferences prefs = getSharedPreferences();
        return prefs.getString(Constants.Preferences.PREF_LOGIN, null);
    }

    public static void setUsername(String username){
        final SharedPreferences prefs = getSharedPreferences();
        prefs.edit().putString(Constants.Preferences.PREF_LOGIN, username).commit();
    }

    public static String getPassword(){
        final SharedPreferences prefs = getSharedPreferences();
        return prefs.getString(Constants.Preferences.PREF_PASSWORD, null);
    }

    public static void setPassword(String password){
        final SharedPreferences prefs = getSharedPreferences();
        prefs.edit().putString(Constants.Preferences.PREF_PASSWORD, password).commit();
    }
    public static void setFirstTime(Boolean b){
        final SharedPreferences prefs = getSharedPreferences();
        prefs.edit().putBoolean(Constants.Preferences.PREF_FirstLaunch,b).apply();
    }
    public static Boolean getFirstTime(){
        final SharedPreferences prefs = getSharedPreferences();
        return prefs.getBoolean(Constants.Preferences.PREF_FirstLaunch, false);
    }
    public static void deletePrefs(){
        final SharedPreferences prefs = getSharedPreferences();
        prefs.edit().clear().commit();
    }
}
