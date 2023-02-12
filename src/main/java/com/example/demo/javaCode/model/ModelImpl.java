package com.example.demo.javaCode.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

@Component
public class ModelImpl implements Model {
    // Contains all alarms, sorted by day of the week
    // Fields
    private HashMap<TimeImpl, Alarm> _m;
    private HashMap<TimeImpl, Alarm> _tu;
    private HashMap<TimeImpl, Alarm> _w;
    private HashMap<TimeImpl, Alarm> _th;
    private HashMap<TimeImpl, Alarm> _f;
    private HashMap<TimeImpl, Alarm> _sa;
    private HashMap<TimeImpl, Alarm> _su;

    // Constructor
    public ModelImpl() {
        _m = new HashMap<TimeImpl, Alarm>();
        _tu = new HashMap<TimeImpl, Alarm>();
        _w = new HashMap<TimeImpl, Alarm>();
        _th = new HashMap<TimeImpl, Alarm>();
        _f = new HashMap<TimeImpl, Alarm>();
        _sa = new HashMap<TimeImpl, Alarm>();
        _su = new HashMap<TimeImpl, Alarm>();
    }

    // Methods
    @Override
    public void createAlarm(TimeImpl time, DAYS_OF_WEEK day, String name) {
        if (day == DAYS_OF_WEEK.MONDAY) {
            _m.put(time, new AlarmImpl(time, name));
        } else if (day == DAYS_OF_WEEK.TUESDAY) {
            _tu.put(time, new AlarmImpl(time, name));
        } else if (day == DAYS_OF_WEEK.WEDNESDAY) {
            _w.put(time, new AlarmImpl(time, name));
        } else if (day == DAYS_OF_WEEK.THURSDAY) {
            _th.put(time, new AlarmImpl(time, name));
        } else if (day == DAYS_OF_WEEK.FRIDAY) {
            _f.put(time, new AlarmImpl(time, name));
        } else if (day == DAYS_OF_WEEK.SATURDAY) {
            _sa.put(time, new AlarmImpl(time, name));
        } else {
            _su.put(time, new AlarmImpl(time, name));
        }
    }

    @Override
    public void removeAlarm(TimeImpl time, DAYS_OF_WEEK day) {

        if (day == DAYS_OF_WEEK.MONDAY) {
            _m.remove(time);
        } else if (day == DAYS_OF_WEEK.TUESDAY) {
            _tu.remove(time);
        } else if (day == DAYS_OF_WEEK.WEDNESDAY) {
            _w.remove(time);
        } else if (day == DAYS_OF_WEEK.THURSDAY) {
            _th.remove(time);
        } else if (day == DAYS_OF_WEEK.FRIDAY) {
            _f.remove(time);
        } else if (day == DAYS_OF_WEEK.SATURDAY) {
            _sa.remove(time);
        } else {
            _su.remove(time);
        }
    }

    @Override
    public ArrayList<Alarm> getAlarms(DAYS_OF_WEEK day) {
        Set<TimeImpl> set;

        // Get keySet
        if (day == DAYS_OF_WEEK.MONDAY) {
            set = _m.keySet();
        } else if (day == DAYS_OF_WEEK.TUESDAY) {
            set = _tu.keySet();
        } else if (day == DAYS_OF_WEEK.WEDNESDAY) {
            set = _w.keySet();
        } else if (day == DAYS_OF_WEEK.THURSDAY) {
            set = _th.keySet();
        } else if (day == DAYS_OF_WEEK.FRIDAY) {
            set = _f.keySet();
        } else if (day == DAYS_OF_WEEK.SATURDAY) {
            set = _sa.keySet();
        } else {
            set = _su.keySet();
        }

        // Turn into list
        ArrayList<Alarm> list = new ArrayList<>();
        Iterator<TimeImpl> itr = set.iterator();

        if (day == DAYS_OF_WEEK.MONDAY) {
            while (itr.hasNext()) {
                list.add(_m.get(itr.next()));
            }
        } else if (day == DAYS_OF_WEEK.TUESDAY) {
            while (itr.hasNext()) {
                list.add(_tu.get(itr.next()));
            }
        } else if (day == DAYS_OF_WEEK.WEDNESDAY) {
            while (itr.hasNext()) {
                list.add(_w.get(itr.next()));
            }
        } else if (day == DAYS_OF_WEEK.THURSDAY) {
            while (itr.hasNext()) {
                list.add(_th.get(itr.next()));
            }
        } else if (day == DAYS_OF_WEEK.FRIDAY) {
            while (itr.hasNext()) {
                list.add(_f.get(itr.next()));
            }
        } else if (day == DAYS_OF_WEEK.SATURDAY) {
            while (itr.hasNext()) {
                list.add(_sa.get(itr.next()));
            }
        } else {
            while (itr.hasNext()) {
                list.add(_su.get(itr.next()));
            }
        }

        return list;
    }

    @Override
    public Alarm getAlarm(TimeImpl time, DAYS_OF_WEEK day) {
        if (day == DAYS_OF_WEEK.MONDAY) {
            return _m.get(time);
        } else if (day == DAYS_OF_WEEK.TUESDAY) {
            return _tu.get(time);
        } else if (day == DAYS_OF_WEEK.WEDNESDAY) {
            return _w.get(time);
        } else if (day == DAYS_OF_WEEK.THURSDAY) {
            return _th.get(time);
        } else if (day == DAYS_OF_WEEK.FRIDAY) {
            return _f.get(time);
        } else if (day == DAYS_OF_WEEK.SATURDAY) {
            return _sa.get(time);
        } else {
            return _su.get(time);
        }
    }

}
