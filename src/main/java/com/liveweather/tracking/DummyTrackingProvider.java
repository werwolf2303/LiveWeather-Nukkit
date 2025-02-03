package com.liveweather.tracking;

import com.liveweather.exceptions.InvalidLocationException;

public class DummyTrackingProvider implements TrackingProvider {

    @Override
    public boolean isNonCommercial() {
        return true;
    }

    @Override
    public float[] getLatLonForIP(String ip) throws InvalidLocationException {
        return new float[] {0, 0};
    }

    @Override
    public String name() {
        return "DUMMY";
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
    public String friendlyName() {
        return "Dummy";
    }
}
