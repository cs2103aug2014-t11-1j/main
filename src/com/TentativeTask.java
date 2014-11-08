package com;

import java.util.ArrayList;

/**
 *
 * @author Krystal
 */
public class TentativeTask {

    //used by Logic to check for clashes
    private static ArrayList<TimePeriod> globalBlockedTimePeriods;
    private ArrayList<TimePeriod> timePeriods;

    private String event;
    private int position;

    public TentativeTask() {
        timePeriods = new ArrayList<TimePeriod>();
    }

    //Mutators
    public void setEvent(String event) {
        this.event = event;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addTimePeriod(TimePeriod tp) {
        timePeriods.add(tp);
    }

    public void addGlobalBlockedTimePeriods(TimePeriod tp) {
        globalBlockedTimePeriods.add(tp);
    }

    //Accessors
    public static ArrayList<TimePeriod> getGlobalBlockedTimePeriods() {
        return globalBlockedTimePeriods;
    }

    public ArrayList<TimePeriod> getTimePeriods() {
        return timePeriods;
    }

    public String getEvent() {
        return event;
    }

    public int getPosition() {
        return position;
    }
}
