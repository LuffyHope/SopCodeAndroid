package com.example.sopcode;

import android.app.Application;
import android.content.Context;

import com.example.sopcode.utils.activity_manager.ActivityManager;

public class App extends Application {
    public static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        ActivityManager.startWatcher(this);
        this.appContext = this;
    }
}
