package com.liveweather.weatherproviders;

import cn.nukkit.Player;
import com.liveweather.weather.Weather;

import java.io.IOException;

public interface WeatherDataProvider {
    boolean isNonCommercial();
    boolean needsLicenseAttribution();
    String getAttributionText();
    String name();
    String friendlyName();
    Weather getWeather(float lat, float lon, Player player) throws IOException;
    int getRefreshPeriod();
}
