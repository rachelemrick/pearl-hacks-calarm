package com.example.demo.javaCode.model;

public class AlarmStringImpl {
    // Fields
    private String time;
    private String name;
    private String day;

    // Constructor
    public AlarmStringImpl(String time, String name, String day) {
        this.time = time;
        this.name = name;
        this.day = day;
    }

    // Methods
    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    public String getName() {
        return name;
    }

    public AlarmImpl makeAlarmImpl() {
        TimeImpl t = TimeImpl.parse(time);
        System.out.println("Time = " + time);
        AlarmImpl alarm = new AlarmImpl(t, name);
        System.out.println("name = " + name);
        System.out.println("day = " + day);
        return alarm;
    }

}
