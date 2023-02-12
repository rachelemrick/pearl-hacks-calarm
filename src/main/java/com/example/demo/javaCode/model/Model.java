package com.example.demo.javaCode.model;

import java.util.ArrayList;

public interface Model {
    /** Creates an alarm on a given day */
    void createAlarm(TimeImpl time, DAYS_OF_WEEK day, String name);

    /** Removes an alarm on a given day */
    void removeAlarm(TimeImpl time, DAYS_OF_WEEK day);

    /** Gets a list of alarms on a given day */
    ArrayList<Alarm> getAlarms(DAYS_OF_WEEK day);

    /** Gets an alarm at a specific time on a specific day */
    Alarm getAlarm(TimeImpl time, DAYS_OF_WEEK day);
}
