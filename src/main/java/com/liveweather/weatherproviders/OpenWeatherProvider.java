package com.liveweather.weatherproviders;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.google.gson.Gson;
import com.liveweather.PublicValues;
import com.liveweather.apis.OpenWeather;
import com.liveweather.config.ConfigValues;
import com.liveweather.utils.HttpClient;
import com.liveweather.weather.Weather;

import java.io.IOException;

public class OpenWeatherProvider implements WeatherDataProvider {
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
        return "CC BY-SA 4.0 and ODbL (https://creativecommons.org/licenses/by-sa/4.0/, https://opendatacommons.org/licenses/odbl/)";
    }

    @Override
    public String name() {
        return "OPENWEATHER";
    }

    @Override
    public String friendlyName() {
        return "OpenWeather";
    }

    @Override
    public Weather getWeather(float lat, float lon, Player player) throws IOException {
        Float[] latLon = PublicValues.playerStorageProvider.getSettingOfPlayer(player.getUniqueId());
        Weather weather;
        OpenWeather.Weather retWeather = new Gson().fromJson(HttpClient.getString(
                String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s",
                        latLon[0], latLon[1], PublicValues.config.getString(ConfigValues.apiKey.name))
        ), OpenWeather.class).getWeather().get(0);
        switch (retWeather.getId().substring(0, 1)) {
            case "2":
                weather = Weather.THUNDER;
                break;
            case "3":
            case "5":
            case "6":
                weather = Weather.RAIN;
                break;
            case "7":
            case "8":
            default:
                weather = Weather.CLEAR;
                break;
        }
        return weather;
    }

    @Override
    public int getRefreshPeriod() {
        int apiCallsPerMonth = PublicValues.config.getInt(ConfigValues.apiCallsPerMonth.name);
        int callsPerMinuteLimit = PublicValues.config.getInt(ConfigValues.apiCallsPerMinute.name);
        int maxPlayers = Server.getInstance().getMaxPlayers();
        int ticksPerSecond = (int) Server.getInstance().getTicksPerSecond();
        int ticksPerHour = 3600 * ticksPerSecond;
        double totalCallsPerHour = apiCallsPerMonth / 744.0;
        double periodTicksMonthly = ticksPerHour / totalCallsPerHour;
        double executionsPerMinuteAllowed = (double) callsPerMinuteLimit / maxPlayers;
        double periodSecondsMin = 60.0 / executionsPerMinuteAllowed;
        double periodTicksMin = periodSecondsMin * ticksPerSecond;
        return (int) Math.ceil(Math.max(periodTicksMonthly, periodTicksMin));
    }
}
