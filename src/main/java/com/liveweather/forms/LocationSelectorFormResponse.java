package com.liveweather.forms;

public class LocationSelectorFormResponse extends DefaultFormResponse{
    private String country;
    private String state;
    private String city;

    void setCountry(String country) {
        this.country = country;
    }

    void setState(String state) {
        this.state = state;
    }

    void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
