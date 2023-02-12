package com.liveweather.simulator;

import com.liveweather.api.GW;
import com.liveweather.api.GetWeather;
import com.liveweather.instances.InstanceManager;
import com.liveweather.language.Language;

public class APIResponse extends GetWeather implements GW {

    @Override
    public boolean isValid(String city) {
        return true;
    }

    @Override
    public String dumpResults(String city) {
        return "{\"coord\":{\"lon\":8.6434,\"lat\":49.3038},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":291.84,\"feels_like\":291.78,\"temp_min\":288.19,\"temp_max\":293.77,\"pressure\":1023,\"humidity\":77,\"sea_level\":1023,\"grnd_level\":1010},\"visibility\":10000,\"wind\":{\"speed\":1.35,\"deg\":153,\"gust\":2.37},\"clouds\":{\"all\":69},\"dt\":1665997197,\"sys\":{\"type\":2,\"id\":37755,\"country\":\"DE\",\"sunrise\":1665985735,\"sunset\":1666024342},\"timezone\":7200,\"id\":2814670,\"name\":\"Walldorf\",\"cod\":200}";
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    @Override
    public String getWeather(String city) {
        String response = "{\"coord\":{\"lon\":8.6434,\"lat\":49.3038},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":291.84,\"feels_like\":291.78,\"temp_min\":288.19,\"temp_max\":293.77,\"pressure\":1023,\"humidity\":77,\"sea_level\":1023,\"grnd_level\":1010},\"visibility\":10000,\"wind\":{\"speed\":1.35,\"deg\":153,\"gust\":2.37},\"clouds\":{\"all\":69},\"dt\":1665997197,\"sys\":{\"type\":2,\"id\":37755,\"country\":\"DE\",\"sunrise\":1665985735,\"sunset\":1666024342},\"timezone\":7200,\"id\":2814670,\"name\":\"Walldorf\",\"cod\":200}";
        String[] conv = response.split(",");
        String out = conv[3].replace("\"", "").replace(":", "").replace("main", "");
        if (conv[3].contains("main")) {
            switch(out) {
                case "Clouds":
                    return "Clear";
                case "Thunderstorm":
                    return "Thunderstorm";
                case "Drizzle":
                    return "Drizzle";
                case "Rain":
                    return "Rain";
                case "Snow":
                    return "Snow";
                case "Clear":
                    return "Clear";
                case "Mist":
                    return "Clear";
                default:
                    InstanceManager.getLogger().critical(new Language().get("liveweather.api.unhandled") + out);
                    return "Clear";
            }
        }
        return null;
    }

    @Override
    public String getLon(String l) {
        return null;
    }

    @Override
    public String getLat(String l) {
        return null;
    }
}
