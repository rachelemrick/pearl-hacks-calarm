package com.example.demo.javaCode.model;

import java.util.Objects;

public class TimeImpl extends Object {
    // Fields
    private int _hour;
    private int _minute;
    private int _second;

    // Constructor
    public TimeImpl(int h, int m, int s) {
        _hour = h;
        _minute = m;
        _second = s;
    }

    // Methods
    public int getHour() {
        return _hour;
    }

    public int getMinute() {
        return _minute;
    }

    public int getSecond() {
        return _second;
    }

    public static TimeImpl parse(String str){
        // Specifically for HTML time data type parsing
        int hours = Integer.valueOf(str.substring(0, 2));
        int minutes = Integer.valueOf(str.substring(3, 5));
        TimeImpl retVal = new TimeImpl(hours, minutes, 0);
        return retVal;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TimeImpl)) {
            return false;
        }
        TimeImpl t2 = (TimeImpl) o;
        if (this.getHour() == t2.getHour()
        && this.getMinute() == t2.getMinute()
        && this.getSecond() == t2.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._hour, this._minute, this._second);
    }
}
