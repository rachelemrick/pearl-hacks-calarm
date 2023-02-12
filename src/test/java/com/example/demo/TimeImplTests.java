package com.example.demo;

import com.example.demo.javaCode.model.TimeImpl;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class TimeImplTests {
    @Test
    public void equalsTest() {
        TimeImpl t1 = new TimeImpl(8, 30, 0);
        TimeImpl t2 = new TimeImpl(8, 30, 0);
        assert(t1.equals(t2));
    }

    @Test
    public void hashMapTest() {
        TimeImpl t1 = new TimeImpl(8, 30, 0);
        TimeImpl t2 = new TimeImpl(8, 30, 0);
        HashMap<TimeImpl, String> hm = new HashMap<>();
        hm.put(t1, "hi");
        hm.remove(t2);
        assert(hm.isEmpty());
    }

}
