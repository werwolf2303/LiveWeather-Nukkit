package com.liveweather.tracking;

import com.google.gson.Gson;
import com.liveweather.apis.IPAPi;
import com.liveweather.exceptions.InvalidLocationException;
import com.liveweather.logging.LWLogging;
import com.liveweather.utils.HttpClient;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;

public class IPAPiProvider implements TrackingProvider {
    @Nullable
    private IPAPi getForIP(String ip) {
        try {
            return new Gson().fromJson(HttpClient.getString(String.format("https://ip-api.com/json/%s?fields=status,message,continent,country,regionName,city,lat,lon&", ip)), IPAPi.class);
        } catch (IOException e) {
            LWLogging.error("Failed to get location for ip");
            LWLogging.throwable(e);
        }
        return null;
    }

    @Override
    public boolean isNonCommercial() {
        return true;
    }

    @Override
    public float[] getLatLonForIP(String ip) throws InvalidLocationException {
        IPAPi response = getForIP(ip);
        return new float[] {response.getLat(), response.getLon()};
    }

    @Override
    public String name() {
        return "IPAPI";
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
        return "IPAPi";
    }

    @Override
    public void init() {

    }

    @Override
    public void unload() {

    }
}
