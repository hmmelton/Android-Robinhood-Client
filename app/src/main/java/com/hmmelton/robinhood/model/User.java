package com.hmmelton.robinhood.model;

import android.content.SharedPreferences;

import com.hmmelton.robinhood.RobinhoodApplication;

/**
 * Created by harrison on 6/21/15.
 */
public class User {

    private static String username;
    private static String password;
    private static String auth_token;

    private static final String PREFS_FILE = "UserPreferences";
    private static final String AUTH_TOKEN_KEY = "com.hmmelton.robinhood.auth_token";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getAuthToken() {
        return auth_token;
    }

    public static void setAuthToken(String token) {
        auth_token = token;
        updateCache();
    }

    private static void updateCache() {
        SharedPreferences preferences =
                RobinhoodApplication.getInstance().getSharedPreferences(PREFS_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(AUTH_TOKEN_KEY, auth_token);
        editor.commit();
    }
}
