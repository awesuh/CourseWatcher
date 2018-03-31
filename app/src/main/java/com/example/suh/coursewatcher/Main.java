package com.example.suh.coursewatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * <h1>class Main</h1>
 * <p>The apps main class. This class manages the app</p>
 */
public class Main extends AppCompatActivity {
    SharedPreferences mPrefs  ;
    SharedPreferences.Editor prefsEditor ;

    public static final String EXTRA_MESSAGE = "com.example.suh.ScriptureSaver.MESSAGE";

    public static String TAG = "MyMain";

    /**
     * <h1>>On Create</h1
     * <p>This function sets the view and handles other tasks necessary on start-up</p>
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setContentView(R.layout.search_results);
        setContentView(R.layout.section_list);
//        setContentView(R.layout.course_info);

        String Owner = "CLARA BISHOP";
        String Co_Owners = "Ben and Jose";
        String THE_Ownerjk = "Jose...";

        mPrefs = getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();


        loadCourse("FDREL275","2018;SP");
    }

    /**
     * <h1>loadUser</h1>
     * <p>This function converts json into a user object</p>
     *
     * @return user object
     */
    public User loadUser(){
        Gson gson = new Gson();
        String json = mPrefs.getString("user_settings", "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }

    /**
     * <h1>saveUser</h1>
     * <p>this function converts json into a user object and saves it</p>
     *
     * @param user
     */
    public void saveUser(User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user_settings", json);
        prefsEditor.commit();
    }

    public void addCourse(Course course){
        // Set course code
        ((TextView) findViewById(R.id.course_code)).setText(course.code);
        // Set code title
        ((TextView) findViewById(R.id.course_title)).setText(course.title);
        // Set description
        ((TextView) findViewById(R.id.description)).setText(course.des);
    }

    private void loadCourse(String courseCode, String semester){
        // Load the course information
        ApiWrapper.getCourse(courseCode,new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Course course = snapshot.getValue(Course.class);
                addCourse(course);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

        // Load all of the sections
        ApiWrapper.getSections(semester,courseCode,new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot sectionSnapshot : snapshot.getChildren()){
                    Section section = sectionSnapshot.getValue(Section.class);
                    addSection(section);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    public void addSection(Section section){
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = findViewById(R.id.TheList);
        View sectionView = layoutInflater.inflate(R.layout.section, (ViewGroup) parent, false);

        // Set the Section Number
        ((TextView) sectionView.findViewById(R.id.SectionNum)).setText(Integer.toString(section.section));

        // Set the Instructor Information
        if(section.instructors != null && !section.instructors.isEmpty()){
            // Build Name
            String instructorName = section.instructors.get(0).last + ", " + section.instructors.get(0).first;
            // Get Name Element
            TextView NameElement = sectionView.findViewById(R.id.TeacherName);
            if(section.instructors.get(0).accounts != null && !section.instructors.get(0).accounts.isEmpty()){
                // Set Rating
                ((RatingBar) sectionView.findViewById(R.id.TeacherRating)).setRating((int) section.instructors.get(0).avgRating);
                //HyperLink stuff
                NameElement.setMovementMethod(LinkMovementMethod.getInstance());
                Spanned text = Html.fromHtml("<a href='http://www.ratemyprofessors.com/ShowRatings.jsp?tid="+
                        section.instructors.get(0).accounts.get(0).id+"'>"+instructorName+"</a>");
                NameElement.setText(text);
            } else {
                // Just set the name
                NameElement.setText(instructorName);
            }
        }

        // Set the Schedule Information
        if(section.schedules != null && !section.schedules.isEmpty()){
            // Set Location
            ((TextView) sectionView.findViewById(R.id.Location)).setText(section.schedules.get(0).location);
            // Set Time
            ((TextView) sectionView.findViewById(R.id.Time)).setText(section.schedules.get(0).time);
            // Set
            ((TextView) sectionView.findViewById(R.id.Method)).setText(section.schedules.get(0).method);
            String days = "";
            if(section.schedules.get(0).days != null){
                for(String day : section.schedules.get(0).days){
                    if(day != null && !day.isEmpty()){
                        days += day;
                    }
                }
            }
            ((TextView) sectionView.findViewById(R.id.Days)).setText(days);
        }

        // Add everything to the screen
        parent.addView(sectionView);

        Log.d(TAG,"Added "+section.code);
    }
}