package com.liveweather.tracking;

import com.liveweather.exceptions.InvalidLocationException;

public interface TrackingProvider {
    boolean isNonCommercial();
    float[] getLatLonForIP(String ip) throws InvalidLocationException;
    String name();
    boolean needsLicenseAttribution();
    String getAttributionText();
    String friendlyName();
}
