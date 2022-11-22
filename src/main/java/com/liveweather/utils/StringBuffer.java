package com.liveweather.utils;

import java.util.ArrayList;

public class StringBuffer {
    ArrayList<String> concept = new ArrayList<>();
    public void add(String s) {
        concept.add(s);
    }
    public void add(String[] s) {
        for(String st : s) {
            concept.add(st);
        }
    }
    public void remove(String s) {
        int count = 0;
        for(String st : concept) {
            if(st.equals(s)) {
                concept.remove(count);
                break;
            }
            count++;
        }
    }
    public void remove(int index) {
        concept.remove(index);
    }
    @Override
    public String toString() {
        String cache = "";
        for(String s : concept.toArray(new String[0])) {
            if(cache.equals("")) {
                cache = s;
            }else{
                cache = cache + " " + s;
            }
        }
        return cache;
    }
    public void removeAll() {
        concept.clear();
    }
}
