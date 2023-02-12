package com.example.demo.javaCode.model;

public class AlarmImpl implements Alarm {
    // Fields
    private TimeImpl _time;
    private String _name;

    // Constructor
    public AlarmImpl(TimeImpl time, String name) {
        _time = time;
        _name = name;
    }

    // Methods
    @Override
    public TimeImpl getTime() {
        return _time;
    }

    @Override
    public String getName() {
        return _name;
    }

}
