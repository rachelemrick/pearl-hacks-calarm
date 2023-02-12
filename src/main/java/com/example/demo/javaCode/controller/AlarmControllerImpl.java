package com.example.demo.javaCode.controller;


import com.example.demo.javaCode.model.Model;
import com.example.demo.javaCode.model.DAYS_OF_WEEK;
import com.example.demo.javaCode.model.TimeImpl;
import org.springframework.stereotype.Component;

@Component
public class AlarmControllerImpl implements AlarmController {
    // Fields
    private Model _model;

    // Constructor
    AlarmControllerImpl(Model model) {
        _model = model;
    }

    // Methods
    @Override
    public void createAlarm(TimeImpl time, DAYS_OF_WEEK day, String name) {
        _model.createAlarm(time, day, name);
    }

    @Override
    public void removeAlarm(TimeImpl time, DAYS_OF_WEEK day) {
        _model.removeAlarm(time, day);
    }
}
