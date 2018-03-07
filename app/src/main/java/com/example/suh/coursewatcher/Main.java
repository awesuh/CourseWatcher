package com.example.suh.coursewatcher;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Main extends AppCompatActivity {
    SharedPreferences mPrefs  ;
    SharedPreferences.Editor prefsEditor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.search_results);
//        setContentView(R.layout.section_list);

        String Owner = "CLARA BISHOP";
        String Co_Owners = "Ben and Jose";
        String THE_Ownerjk = "Jose...";

        //Hi
        mPrefs = getDefaultSharedPreferences(this);
        prefsEditor = mPrefs.edit();

        getObject();
    }

    public User getObject(){
        Gson gson = new Gson();
        String json = mPrefs.getString("user_settings", "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }

    public void saveObject(User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user_settings", json);
        prefsEditor.commit();
    }
}