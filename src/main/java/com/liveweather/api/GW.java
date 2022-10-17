package com.liveweather.api;

public interface GW {
    public boolean isValid(String city);
    public String dumpResults(String city);
    public String getWeather(String city);
    public String getLon(String l);
    public String getLat(String l);
}
