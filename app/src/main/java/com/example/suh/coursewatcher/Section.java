package com.example.suh.coursewatcher;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.ArrayList;

/**
 * <h1>Section</h1>
 *
 * Contains all of the section information,
 * watchout for sections that contain more than one instructor or schedules
 */

public class Section {
    public static final String TAG = "Section";

    public String code;
    public String course;
    public int credits;
    ArrayList<Instructor> instructors;
    public String name;
    ArrayList<Schedule> schedules;
    public int seatsFilled;
    public int seatsTotal;
    public int section;
    public String session;
    public String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public int getSeatsFilled() {
        return seatsFilled;
    }

    public void setSeatsFilled(int seatsFilled) {
        this.seatsFilled = seatsFilled;
    }

    public int getSeatsTotal() {
        return seatsTotal;
    }

    public void setSeatsTotal(int seatsTotal) {
        this.seatsTotal = seatsTotal;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Section(){
//        Log.d(TAG,"Creating a section");
    }

    public Section(String code, String course, int credits, ArrayList<Instructor> instructors,
                   String name, ArrayList<Schedule> schedules, int seatsFilled, int seatsTotal,
                   int section, String session, String status) {
        this.code = code;
        this.course = course;
        this.credits = credits;
        this.instructors = instructors;
        this.name = name;
        this.schedules = schedules;
        this.seatsFilled = seatsFilled;
        this.seatsTotal = seatsTotal;
        this.section = section;
        this.session = session;
        this.status = status;
    }
}
