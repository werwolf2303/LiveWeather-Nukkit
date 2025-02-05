package com.liveweather.config;

// In reversed order
public enum ConfigValues {
    apiCallsPerMinute("weather.apicallsperminute", "How many api calls the plugin can make per minute", 600),
    apiCallsPerMonth("weather.apicallspermonth", "How many api calls the plugin cmake make per month", 10000000),
    apiKey("weather.apikey", "The api key for the weather api you want to use", "YOUR_API_KEY"),
    apiProvider("weather.provider", "The weather api provider you want to use (OPENMETEO, OPENMETEOCOMMERCIAL, DUMMY)", "OPENWEATHER"),
    trackingKey("tracking.apikey", "The api key for the tracking api you want to use (Only if autofindplayercity is true)", "YOUR_API_KEY"),
    trackingProvider("tracking.provider", "The api provider you want to use for tracking (IPAPI, IPAPICOMMERCIAL, DUMMY)", "IPAPI"),
    storagePassword("storage.password", "The password you want to sue (Not for SQLITE)", "YOUR_STORAGE_PASSWORD"),
    storageUsername("storage.username", "The username you want to use (Not for SQLITE)", "YOUR_STORAGE_USERNAME"),
    storageIP("storage.ip", "The ip address where your sql server instance lives (Not for SQLITE)", "YOUR_IP"),
    storageType("storage.type", "The storage type you want to use (SQLITE, MYSQL, DUMMY, JSON, RAM)", "JSON"),
    geocodingAPIKey("geocoding.apikey", "The api key for the provider you want to use", "YOU_API_KEY"),
    geocodingProvider("geocoding.provider", "The provider for the geocoding (Only if autofindplayercity is true) possible: (GEOAPIFY, DUMMY)", "GEOAPIFY"),
    debug("general.debug", "Set this to true if you want to have debugging features", false),
    autoFind("general.autofindplayercity", "Set this to true if you want that the location of the players get automatically set via ip tracking", false),
    language("general.language", "The language LiveWeather should use", "en");
    public final String name;
    public final String comment;
    public final Object defaultValue;
    ConfigValues(String name, String comment, Object defaultValue) {
        this.name = name;
        this.comment = comment;
        this.defaultValue = defaultValue;
    }
}
