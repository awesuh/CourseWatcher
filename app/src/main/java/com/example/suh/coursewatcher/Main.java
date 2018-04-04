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
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.search_results);
//        setContentView(R.layout.section_list);
//        setContentView(R.layout.course_info);

        String Owner = "CLARA BISHOP";
        String Co_Owners = "Ben and Jose";
        String THE_Ownerjk = "Jose...";

        mPrefs = getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();


        // loadCourse("FDREL275","2018;SP");
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

    public void callSectionList(View view) {

        Intent intent = new Intent(this, SectionListActivity.class);

        String courseCode = "CS246";
        String semester = "2018;SP";
        intent.putExtra("courseCode", courseCode);
        intent.putExtra("semester", semester);

        startActivity(intent);
    }

}