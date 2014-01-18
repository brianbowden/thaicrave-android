package com.thaicrave.android.app;

import android.app.Application;

public class TcApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        // Set Environment here
        TcEnv.init(TcEnv.ENV.LOCAL);
        Volleyball.init(this);
    }
}
