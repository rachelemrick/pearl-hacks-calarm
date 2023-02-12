package com.example.demo.javaCode.controller;


import com.example.demo.javaCode.model.DAYS_OF_WEEK;
import com.example.demo.javaCode.model.TimeImpl;

public interface AlarmController {
    /** Creates an alarm */
    void createAlarm(TimeImpl time, DAYS_OF_WEEK day, String name);

    /** Removes an alarm */
    void removeAlarm(TimeImpl time, DAYS_OF_WEEK day);
}
