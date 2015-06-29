package com.hmmelton.robinhood.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by harrison on 6/21/15.
 */
public class User {

    private static String auth_token;

    private static final String PREFS_FILE = "UserPreferences";
    private static final String AUTH_TOKEN_KEY = "com.hmmelton.robinhood.auth_token";

    private static Context mContext;

    public User(Context context) {
        this.mContext = context;

        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_FILE, 0);
        auth_token = preferences.getString(AUTH_TOKEN_KEY, null);
    }

    public static String getAuthToken() {
        return auth_token;
    }

    public static void setAuthToken(String token) {
        auth_token = token;
        updateCache();
    }

    private static void updateCache() {
        SharedPreferences preferences = mContext.getSharedPreferences(PREFS_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(AUTH_TOKEN_KEY, auth_token);
        editor.commit();
    }
}
