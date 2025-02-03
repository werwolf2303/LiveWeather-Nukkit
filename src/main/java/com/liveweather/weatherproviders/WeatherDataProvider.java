package com.liveweather.weatherproviders;

import java.io.IOException;

public interface WeatherDataProvider {
    boolean isCityValid(String city) throws IOException;
    boolean isNonCommercial();
    boolean needsLicenseAttribution();
    String getAttributionText();
    String name();
    String friendlyName();
}
