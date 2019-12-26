package com.example.sopcode.utils;

import android.view.View;


public class EventHelper {

    public static void click(View.OnClickListener li, View... views) {
        if (views == null || views.length == 0) return;
        for (View v : views) v.setOnClickListener(li);
    }
}
