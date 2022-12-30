package com.liveweather.api;

public interface GW {
    boolean isValid(String city);
    String dumpResults(String city);
    String getWeather(String city);
    String getLon(String l);
    String getLat(String l);
}
