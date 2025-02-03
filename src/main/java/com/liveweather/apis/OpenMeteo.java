package com.liveweather.apis;

import java.util.List;

public class OpenMeteo {
    private int latitude;
    private int longitude;
    private int generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private int elevation;
    private DailyUnits daily_units;
    private Daily daily;

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getGenerationtime_ms() {
        return generationtime_ms;
    }

    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public int getElevation() {
        return elevation;
    }

    public DailyUnits getDaily_units() {
        return daily_units;
    }

    public Daily getDaily() {
        return daily;
    }


    public static class DailyUnits {
        private String time;
        private String weather_code;

        public String getTime() {
            return time;
        }

        public String getWeather_code() {
            return weather_code;
        }
    }

    public static class Daily {
        private List<String> time;
        private List<Integer> weather_code;

        public List<String> getTime() {
            return time;
        }

        public List<Integer> getWeather_code() {
            return weather_code;
        }
    }
}
