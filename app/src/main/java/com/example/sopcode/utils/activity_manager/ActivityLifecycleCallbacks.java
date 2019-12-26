package com.example.sopcode.utils.activity_manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.Set;


public class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private LinkedList<Activity> activityList;
    private Set<Activity> acaActivitys;
    private boolean isAppBack;
    public Activity tagActivity;

    private LinkedList<Activity> dymanicActivitys;


    private static final String TAG = "ActivityLifecycleCallbacks->";

    public ActivityLifecycleCallbacks(LinkedList<Activity> activityList, Set<Activity> acaActivitys, LinkedList<Activity> dymanicActivitys) {
        this.activityList = activityList;
        this.acaActivitys = acaActivitys;
        this.dymanicActivitys = dymanicActivitys;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        activityList.add(activity);
        Log.d(TAG , activity.getClass().getSimpleName() + " onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d("" , activity.getClass().getSimpleName() + " onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        tagActivity = activity;
        Log.d("" , activity.getClass().getSimpleName() + " onActivityResumed");
        acaActivitys.add(activity);
        if (isAppBack) {
            Log.d("" ,"：：：：：：：：：：：app从后台转到前台");
            isAppBack = false;
        }
        dymanicActivitys.add(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d("" , activity.getClass().getSimpleName() + " onActivityPaused");
        if (dymanicActivitys.contains(activity))
            dymanicActivitys.remove(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d("" , activity.getClass().getSimpleName() + " onActivityStopped");
        if (acaActivitys.contains(activity))
            acaActivitys.remove(activity);
        if (acaActivitys.size() == 0) {
            Log.d("" ,"：：：：：：：：：：：应用退出到后台");
            isAppBack = true;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d("" , activity.getClass().getSimpleName() + " onActivityDestroyed");
        if (activityList.contains(activity))
            activityList.remove(activity);
    }
}
