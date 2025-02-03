package com.liveweather.apis;

import java.util.List;

public class Geoapify {
    private List<GeopaifyResult> results;

    public List<GeopaifyResult> getResults() {
        return results;
    }

    public static class GeopaifyResult {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }

        public float getLat() {
            return lat;
        }
    }
}
