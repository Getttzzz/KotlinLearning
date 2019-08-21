package com.example.user.javalearning;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

    @Test
    public void testMap() {
        int a = 1 << 4; // power of two, it's 16, it's initial capacity of table[] inside hash map
        //Generally, searching, inserting, and removing complexities are O(1).
        //Worst case for these operations are O(n).

        final Map<String, String> digits = new HashMap<>();

        digits.put("1", "one");
        digits.put("2", "two");
        digits.put("3", "three");

        Collection<String> values = digits.values();
        Set<String> keySet = digits.keySet();

        //Iterate hash map
        for (Map.Entry<String, String> entry : digits.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("GETZ.HashMapTest.testMap ---> key=" + key + " val=" + value);
        }
    }
}
