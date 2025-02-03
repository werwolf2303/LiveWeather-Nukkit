package com.liveweather.weatherproviders;

public class DummyWeatherProvider implements WeatherDataProvider {
    @Override
    public boolean isCityValid(String city) {
        return false;
    }

    @Override
    public boolean isNonCommercial() {
        return true;
    }

    @Override
    public boolean needsLicenseAttribution() {
        return false;
    }

    @Override
    public String getAttributionText() {
        return "";
    }

    @Override
    public String name() {
        return "DUMMY";
    }

    @Override
    public String friendlyName() {
        return "Dummy";
    }
}
