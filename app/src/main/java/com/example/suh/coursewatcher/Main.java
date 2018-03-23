package com.example.suh.coursewatcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        //Hi
        mPrefs = getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();

        getObject();

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

        ApiWrapper.getSections("2018;SP","FDREL275",new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot sectionSnapshot : snapshot.getChildren()){
                    Section section = sectionSnapshot.getValue(Section.class);
                    addSection(section);
                }
//                Course course = snapshot.getValue(Course.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
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

    public void addSection(Section section){
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = findViewById(R.id.TheList);
        View sectionView = layoutInflater.inflate(R.layout.section, (ViewGroup) parent, false);

        ((TextView) sectionView.findViewById(R.id.SectionNum)).setText(Integer.toString(section.section));

        String instructorName = section.instructors.get(0).last + ", " + section.instructors.get(0).first;
        ((TextView) sectionView.findViewById(R.id.TeacherName)).setText(instructorName);
        ((RatingBar) sectionView.findViewById(R.id.TeacherRating)).setRating((int) section.instructors.get(0).avgRating);

        ((TextView) sectionView.findViewById(R.id.Location)).setText(section.schedules.get(0).location);
        ((TextView) sectionView.findViewById(R.id.Time)).setText(section.schedules.get(0).time);
        ((TextView) sectionView.findViewById(R.id.Method)).setText(section.schedules.get(0).method);
        String days = "";
        if(section.schedules.get(0).days != null){
            for(String day : section.schedules.get(0).days){
                if(day != null){
                    days += day;
                }
            }
        }
        ((TextView) sectionView.findViewById(R.id.Days)).setText(days);

        parent.addView(sectionView);
        Log.d(TAG,"Added "+section.code);
    }
}