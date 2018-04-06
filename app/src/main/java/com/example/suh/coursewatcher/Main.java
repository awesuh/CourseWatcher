package com.example.suh.coursewatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.Arrays;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * <h1>class Main</h1>
 * <p>The apps main class. This class manages the app</p>
 */
public class Main extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.suh.ScriptureSaver.MESSAGE";

    public static String TAG = "MyMain";

    ArrayAdapter<String> adapter;

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

        loadSubscribed();
    }

    private void loadSubscribed(){
        User user = loadUser();
        if(user != null){
            final ListView lv = findViewById(R.id.subscribed_list);
            adapter = new ArrayAdapter<String>(
                    Main.this,
                    android.R.layout.simple_list_item_1);

            lv.setAdapter(adapter);
            adapter.addAll(user.courses);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String courseCode = lv.getItemAtPosition(position).toString().trim();

                    Intent intent = new Intent(Main.this, SectionListActivity.class);
                    String semester = "2018;SP";
                    intent.putExtra("courseCode", courseCode);
                    intent.putExtra("semester", semester);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * <h1>loadUser</h1>
     * <p>This function converts json into a user object</p>
     *
     * @return user object
     */
    public User loadUser(){
        Gson gson = new Gson();
        SharedPreferences mPrefs = getDefaultSharedPreferences(this);
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
        SharedPreferences mPrefs = getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("user_settings", json);
        prefsEditor.apply();
    }

    public void callSectionList(View view) {

        Intent intent = new Intent(this, SectionListActivity.class);

        String courseCode = "CS246";
        String semester = "2018;SP";
        intent.putExtra("courseCode", courseCode);
        intent.putExtra("semester", semester);

        startActivity(intent);
    }

    public void goToSearch(View view){
        Intent intent = new Intent(this, SearchResultsActivity.class);
        startActivity(intent);
    }
}