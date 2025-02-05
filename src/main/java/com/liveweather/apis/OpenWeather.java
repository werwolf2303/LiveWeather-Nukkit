package com.liveweather.apis;

import java.util.List;

public class OpenWeather {
    private List<Weather> weather;

    public static class Weather {
        private String id;
        private String main;
        private String description;
        private String icon;

        public String getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
