package com.liveweather.geocoding;

import com.google.gson.Gson;
import com.liveweather.PublicValues;
import com.liveweather.apis.Geoapify;
import com.liveweather.config.ConfigValues;
import com.liveweather.exceptions.InvalidLocationException;
import com.liveweather.utils.HttpClient;

import java.io.IOException;
import java.net.URLEncoder;

public class GeoapifyGeocodingProvider implements GeocodingProvider {
    @Override
    public String name() {
        return "GEOAPIFY";
    }

    @Override
    public String friendlyName() {
        return "Geoapify";
    }

    @Override
    public boolean isNonCommercial() {
        return true;
    }

    @Override
    public boolean needsLicenseAttribution() {
        return true;
    }

    @Override
    public String getAttributionText() {
        return "Powered by Geoapify (https://www.geoapify.com/)";
    }

    @Override
    public float[] getLonLat(String country, String state, String city) throws IOException, InvalidLocationException {
        Geoapify response = new Gson().fromJson(HttpClient.getString(String.format(
                "%s%s %s %s%s%s",
                "https://api.geoapify.com/v1/geocode/search?text=",
                URLEncoder.encode(country, "UTF-8"),
                URLEncoder.encode(state, "UTF-8"),
                URLEncoder.encode(city, "UTF-8"),
                "&format=json&apiKey=",
                PublicValues.config.getString(ConfigValues.geocodingAPIKey.name)
        ).replace(" ", "%20")), Geoapify.class);
        if(response.getResults().isEmpty()) throw new InvalidLocationException();
        Geoapify.GeopaifyResult result = response.getResults().get(0);
        return new float[] {
                result.getLon(),
                result.getLat()
        };
    }
}
