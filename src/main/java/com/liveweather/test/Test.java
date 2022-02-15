package com.liveweather.test;

import com.liveweather.api.GetWeather;

public class Test {
    public static void main(String[] args) {
        GetWeather weather = new GetWeather();
        System.out.println(weather.getWeather("Hemsbach"));
    }
}
