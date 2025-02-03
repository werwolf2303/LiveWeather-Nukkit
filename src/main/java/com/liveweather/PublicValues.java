package com.liveweather;


import com.liveweather.config.Config;
import com.liveweather.geocoding.GeocodingProvider;
import com.liveweather.language.libLanguage;
import com.liveweather.storage.Storage;
import com.liveweather.tracking.TrackingProvider;
import com.liveweather.weatherproviders.WeatherDataProvider;

import java.io.File;

public class PublicValues {
    public static File pluginpath;
    public static File configPath;
    public static Config config;
    public static libLanguage language;
    public static TrackingProvider trackingProvider;
    public static TrackingProvider[] trackingProviders;
    public static WeatherDataProvider weatherDataProvider;
    public static WeatherDataProvider[] weatherDataProviders;
    public static Storage playerStorageProvider;
    public static Storage[] playerStorageProviders;
    public static GeocodingProvider[] geocodingProviders;
    public static GeocodingProvider geocodingProvider;
}
