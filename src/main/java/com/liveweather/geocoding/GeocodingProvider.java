package com.liveweather.geocoding;

import com.liveweather.exceptions.InvalidLocationException;

import java.io.IOException;

public interface GeocodingProvider {
    String name();
    String friendlyName();
    boolean isNonCommercial();
    boolean needsLicenseAttribution();
    String getAttributionText();
    float[] getLonLat(String country, String state, String city) throws IOException, InvalidLocationException;
}
