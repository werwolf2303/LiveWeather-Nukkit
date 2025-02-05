package com.liveweather.weatherproviders;

import cn.nukkit.Player;
import com.liveweather.weather.Weather;

import java.io.IOException;

public class OpenMeteoProvider implements WeatherDataProvider {
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
        return "CC BY 4.0 (https://creativecommons.org/licenses/by/4.0/)";
    }

    @Override
    public String name() {
        return "OPENMETEO";
    }

    @Override
    public String friendlyName() {
        return "Open-Meteo";
    }

    @Override
    public Weather getWeather(float lat, float lon, Player player) throws IOException {
        return null;
    }

    @Override
    public int getRefreshPeriod() {
        return 0;
    }
}
