package com.hmmelton.robinhood;

import android.app.Application;

import com.hmmelton.robinhood.model.User;
import com.hmmelton.robinhood.utils.RobinhoodApi;

/**
 * Created by harrison on 6/21/15.
 */
public class RobinhoodApplication extends Application {

    private static RobinhoodApi api;
    private static RobinhoodApplication instance;
    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        user = new User(instance);
    }

    public static RobinhoodApi getApi() {
        return api;
    }

    public static RobinhoodApplication getInstance() {
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public static void initApi(String username, String password) {
        api = new RobinhoodApi(instance, username, password);
    }
}
