package com.example.quizzy.utils;

public class Constants {
    public class General {
        public static final String LOG_TAG = "Quizzy";
    }

    public class Login {
        public static final String EXTRA_LOGIN = "extraLogin";
    }

    public class Preferences {
        public static final String SHARED_PREFERENCES_FILE_NAME = "quizzySharedPrefs";
        public static final String PREF_LOGIN = "prefLogin";
        public static final String PREF_PASSWORD = "prefPassword";
        public static final String PREF_FirstLaunch = "prefFirstLaunch";
    }

    public class OpenQuizzdb {
        public static final String URL_TOKEN = "https://api.twitter.com/oauth2/token";
        public static final String URL_STREAM = "https://opentdb.com/";
        //public static final String URL_STREAM = "https://www.openquizzdb.org/api.php?key=9DP29A259F&amount=10&choice=4";

        public static final String API_KEY = "9DP29A259F";
        public static final String API_SECRET = "YNLBrvGSNXZs4H8Thpjz3isCS6UqAH3u2gyG7zHpBzYhiJLTiW";
        public static final String DEFAULT_USERNAME = "rd_aw";
    }

    public class Tweet {
        public static final String EXTRA_IMAGEURL = "extraImageUrl";
        public static final String EXTRA_NAME = "extraName";
        public static final String EXTRA_ALIAS = "extraAlias";
        public static final String EXTRA_TEXT = "extraText";
    }
}
