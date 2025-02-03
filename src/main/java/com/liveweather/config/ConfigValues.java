package com.liveweather.config;

public enum ConfigValues {
    apiKey("weather.apikey", "The api key for the weather api you want to use", "YOUR_API_KEY"),
    apiProvider("weather.apiType", "The weather api provider you want to use (OPENMETEO, OPENMETEOCOMMERCIAL, DUMMY)", "OPENMETEO"),
    trackingKey("tracking.trackingkey", "The api key for the tracking api you want to use (Only if autofindplayercity is true)", "YOUR_API_KEY"),
    trackingProvider("tracking.trackingProvider", "The api provider you want to use for tracking (IPAPI, IPAPICOMMERCIAL, DUMMY)", "IPAPI"),
    storageType("storage.storagetype", "The storage type you want to use (SQLITE, MYSQL, DUMMY, JSON)", "JSON"),
    storageIP("storage.storageip", "The ip address where your sql server instance lives (Not for SQLITE)", "YOUR_IP"),
    storageUsername("storage.storageusername", "The username you want to use (Not for SQLITE)", "YOUR_STORAGE_USERNAME"),
    storagePassword("storage.storagepassword", "The password you want to sue (Not for SQLITE)", "YOUR_STORAGE_PASSWORD"),
    geocodingProvider("geocoding.provider", "The provider for the geocoding (Only if autofindplayercity is true) possible: (GEOAPIFY, DUMMY)", "GEOAPIFY"),
    geocodingAPIKey("geocoding.apikey", "The api key for the provider you want to use", "YOU_API_KEY"),
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
