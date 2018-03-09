package com.example.suh.coursewatcher;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Enano on 3/5/18.
 */

public class ApiWrapper {

    public static String TAG = "ApiWrapper";

    public void example(){
        ValueEventListener courseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot sectionSnapshot : snapshot.getChildren()){
                    Section section = sectionSnapshot.getValue(Section.class);
                    Log.d(TAG, section.code);
                }
//                Course course = snapshot.getValue(Course.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        this.getSections("2018;SP","CS246",courseListener);
    }

    public static void getCourse(String courseCode,ValueEventListener courseListener) {
        String ref = "/courses/"+courseCode;
        DatabaseReference courseRef = FirebaseDatabase.getInstance().getReference(ref);
        Log.i(TAG, "getting " + ref);
        courseRef.addValueEventListener(courseListener);
    }

    public static void getSections(String semester,String courseCode,ValueEventListener courseListener) {
        String ref = "/sections/"+semester+"/"+courseCode;
        DatabaseReference courseRef = FirebaseDatabase.getInstance().getReference(ref);
        Log.i(TAG, "getting " + ref);
        courseRef.addValueEventListener(courseListener);
    }
}