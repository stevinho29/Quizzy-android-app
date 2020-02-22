package com.example.quizzy.jobs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.DatePicker;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidateForm {

    /*
     * A Common function to check internet connection.
     * */
    public static boolean isOnline(Context c) {
        try {
            ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * A common function to check length for input.
     * */
    public static boolean isValidLength(String fName) {
        if (fName.trim().length() > 0) {
            return true;
        }
        return false;
    }

    /*
     * A common function to validate Email id.
     * */
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //validating birthdate provided
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isValidDate(Date date){
        if((date.getTime() - LocalDate.now().getYear()) >= 18){
            return true;
        }

        return false;
    }
    // validating password with retype password
    public static boolean isValidPassword(String password) {
        if (password != null) {
            return true;
        }
        return false;
    }
    //validating password length
    public static boolean isConformPassword(String password){
        if(password.length() > 4)
            return true;
        return false;
    }
    // validating of confirm password
    public static boolean isValidMatchPassword(String pass, String confirmPassword) {
        if (pass.equals(confirmPassword)) {
            return true;
        }
        return false;
    }
}
