package com.example.suh.coursewatcher;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * <h1>ApiWrapper</h1>
 *
 * Wraps the Firebase calls, for simplification though they are already pretty simple
 */

public class ApiWrapper {

    public static String TAG = "ApiWrapper";

    /**
     * Just a function used for testing
     */
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

    /**
     * Sets up the call for getting a course object
     * @param courseCode the course code ex. ('ACCTG180')
     * @param courseListener an instance of ValueEventListener which Overrides the onDataChange method
     *                       to be used as a callback
     */
    public static void getCourse(String courseCode,ValueEventListener courseListener) {
        String ref = "/courses/"+courseCode;
        DatabaseReference courseRef = FirebaseDatabase.getInstance().getReference(ref);
        Log.i(TAG, "getting " + ref);
        courseRef.addValueEventListener(courseListener);
    }

    /**
     * Sets up the call for getting a list of sections
     * @param semester the semester you want the sections from (ex. '2018;SP')
     * @param courseCode the course code (ex. 'ACCTG180')
     * @param courseListener an instance of ValueEventListener which Overrides the onDataChange method
     *                       to be used as a callback
     */
    public static void getSections(String semester,String courseCode,ValueEventListener courseListener) {
        String ref = "/sections/"+semester+"/"+courseCode;
        DatabaseReference courseRef = FirebaseDatabase.getInstance().getReference(ref);
        Log.i(TAG, "getting " + ref);
        courseRef.addValueEventListener(courseListener);
    }
}