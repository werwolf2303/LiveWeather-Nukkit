package com.liveweather.weatherproviders;

import cn.nukkit.Player;
import com.liveweather.weather.Weather;

import java.io.IOException;

public class DummyWeatherProvider implements WeatherDataProvider {
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

    @Override
    public Weather getWeather(float lat, float lon, Player player) throws IOException {
        return null;
    }

    @Override
    public int getRefreshPeriod() {
        return 69;
    }
}
