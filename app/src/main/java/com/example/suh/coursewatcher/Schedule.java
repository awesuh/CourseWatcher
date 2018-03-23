package com.example.suh.coursewatcher;

import android.util.Log;

import java.util.ArrayList;

/**
 * <h1>Schedule</h1>
 *
 * Schedule specifies the sections location in time and space
 */

public class Schedule {
    public static final String TAG = "Schedule";

    public String endTime;
    public String startTime;
    public String location;
    public String method;
    public String time;
    public ArrayList<String> days;

    public Schedule() {
//        Log.d(TAG,"Creating a Schedule");
    }
}
