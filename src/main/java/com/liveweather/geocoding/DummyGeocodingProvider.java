package com.liveweather.geocoding;

import com.liveweather.exceptions.InvalidLocationException;

import java.io.IOException;

public class DummyGeocodingProvider implements GeocodingProvider {
    @Override
    public String name() {
        return "DUMMY";
    }

    @Override
    public String friendlyName() {
        return "Dummy";
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
    public float[] getLonLat(String country, String state, String city) throws IOException, InvalidLocationException {
        return new float[] {};
    }
}
