package com.example.sopcode;

import android.app.Application;

import com.example.sopcode.utils.activity_manager.ActivityManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActivityManager.startWatcher(this);
    }
}
