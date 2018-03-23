package com.example.suh.coursewatcher;


import android.util.Log;

import java.util.ArrayList;

/**
 * <h1>class Instructor</h1>
 * <p>This class contains an instructors name, email address, averaged rate my professor rating, and
 * rate my professor account</p>
 */
public class Instructor {
    public static final String TAG = "Instructor";

    public String first;
    public String last;
    public String middle;
    public String email;
    public float avgRating;
    public ArrayList<RMPAccount> accounts;

    public Instructor() {
//        Log.d(TAG,"Creating an Instructor");
    }
}
