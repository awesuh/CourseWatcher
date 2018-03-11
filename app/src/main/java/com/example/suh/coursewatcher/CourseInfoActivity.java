package com.example.suh.coursewatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Suh on 3/10/2018.
 */

public class CourseInfoActivity extends AppCompatActivity {

    private static final String TAG = "CourseInfoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_info);

        Intent intent = getIntent();
        //String course_code = intent.getStringExtra(Main.course_code);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView3);
        //textView.setText(scripture);
    }
}

