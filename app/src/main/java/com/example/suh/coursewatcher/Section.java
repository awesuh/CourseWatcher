package com.example.suh.coursewatcher;

import java.util.ArrayList;

/**
 * <h1>Section</h1>
 *
 * Contains all of the section information,
 * watchout for sections that contain more than one instructor or schedules
 */

public class Section {
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

    public Section(){

    }

    public Section(String code, String course, int credits, ArrayList<Instructor> instructors,
                   String name, ArrayList<Schedule> schedules, int seatsFilled, int seatsTotal,
                   int section, String session, String status){
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
