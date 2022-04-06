package com.liveweather.test;

import com.liveweather.api.GetWeather;
import com.liveweather.check.APIKey;
import com.liveweather.location.Tracker;

public class Test {
    public static void main(String[] args) {
        System.out.println(new Tracker().getCity("91.2.156.54"));
    }
}
