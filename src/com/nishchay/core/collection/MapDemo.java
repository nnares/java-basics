package com.nishchay.core.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapDemo {

    public static void main(String[] args) {

        hashMapNullPut();

    }


    private static void hashMapNullPut() {

        Map<String, Integer> map = new HashMap<>();

        // null as key
        map.put(null, 1);
        map.put(null, 2);
        map.put(null, 3);

        map.put("a", 10);
        map.put("b", 20);

        // null as value
        map.put("c", null);
        map.put("d", null);
        map.put("e", null);

        // hashMap printing logic

//        System.out.println("map = " + map);

        Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<String, Integer> entry = itr.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

/*
        for (Map.Entry e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
*/

//        map.forEach((key, value) -> System.out.println(key + "-> " + value));

    }

}
