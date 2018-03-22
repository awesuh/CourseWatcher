package com.example.suh.coursewatcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

        //Hi
        mPrefs = getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();

        getObject();

        ApiWrapper apiWrapper = new ApiWrapper();
        apiWrapper.example();

        // List view stuff
        ArrayList<Instructor> instructors =  new ArrayList<Instructor>();
        Instructor Nagash = new Instructor();
        Nagash.first = "Nagash";
        Nagash.last = "unknown";
        Nagash.middle = "";
        Nagash.email = "Nagash@outlook.edu";
        Nagash.avgRating = 5;

        ArrayList<RMPAccount> accounts = new ArrayList<RMPAccount>();
        RMPAccount NagashRMP = new RMPAccount();
        NagashRMP.id = 66;
        NagashRMP.numRatings = 70000002;
        accounts.add(NagashRMP);

        Nagash.accounts = accounts;
        instructors.add(Nagash);

        ArrayList<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        schedule.endTime = "1:00AM";
        schedule.startTime = "12:00PM";
        schedule.location = "Sunny hill cemetery";
        schedule.method = "face-to-face";
        schedule.time = "(04/23/2018 – 07/23/2018)";
        String m = "M";
        String r = "R";
        ArrayList<String> days = new ArrayList<>();
        days.add(m);
        days.add(r);
        schedules.add(schedule);

        Section one = new Section("Necromancy 101", "Basic Necromancy", 2, instructors, "unkown",
                schedules, 99, 100, 1, "winter", "open");

        // This id does not exist anymore, so commenting out the rest of this
//        ListView listView = (ListView) findViewById(R.id.subscribed_list);
//
//        ArrayList<Section> sectionList = new ArrayList<>();
//        sectionList.add(one);
//
//        SectionListAdapter adapter = new SectionListAdapter(this, R.layout.sectioned_list_view, sectionList);
//        listView.setAdapter(adapter);
    }

    /**
     * <h1>getObject</h1>
     * <p>This function converts json into a user object</p>
     *
     * @return user object
     */
    public User getObject(){
        Gson gson = new Gson();
        String json = mPrefs.getString("user_settings", "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }

    /**
     * <h1>saveObject</h1>
     * <p>this function converts json into a user object and saves it</p>
     *
     * @param user
     */
    public void saveObject(User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user_settings", json);
        prefsEditor.commit();
    }

}