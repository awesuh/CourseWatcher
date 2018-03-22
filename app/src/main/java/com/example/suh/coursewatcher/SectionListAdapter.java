package com.example.suh.coursewatcher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>class SectionListAdapter</h1>
 * <p>This class sections off the list view it is applied to</p>
 */

class SectionListAdapter extends ArrayAdapter<Section> {

    private static final String TAG = "SectionListAdapter";

    private Context context;
    private int resource;

    public SectionListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Section> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    /**
     * <h1>getView</h1>
     * <p>This function contains the logic for sectioning off the listView</p>
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the information
        String cousrseCode = getItem(position).course + "-" + getItem(position).code;
        ArrayList<Schedule> schedules = getItem(position).schedules;
        String schedule = schedules.get(0).startTime + "-" + schedules.get(0).endTime + " "
                + schedules.get(0).time;
        ArrayList<Instructor> instructors = getItem(position).instructors;
        String instructor = instructors.get(0).last + ", " + instructors.get(0).first + " "
                + instructors.get(0).middle + " " + instructors.get(0).avgRating;

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);
        Log.d(TAG, "getView: dude");
        TextView tvCourseCode = (TextView) convertView.findViewById(R.id.course_code_view);
        TextView tvSchedule = (TextView) convertView.findViewById(R.id.schedule_view);
        TextView tvInstructor = (TextView) convertView.findViewById(R.id.instructor_view);

        tvCourseCode.setText(cousrseCode);
        tvSchedule.setText(schedule);
        tvInstructor.setText(instructor);

        return convertView;
    }
}
