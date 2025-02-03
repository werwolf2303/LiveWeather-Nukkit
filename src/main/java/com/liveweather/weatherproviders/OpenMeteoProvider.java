package com.liveweather.weatherproviders;

import java.io.IOException;

public class OpenMeteoProvider implements WeatherDataProvider {
    @Override
    public boolean isCityValid(String city) throws IOException {
        return false;
    }

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
}
