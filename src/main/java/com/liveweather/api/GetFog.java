package com.liveweather.api;

public class GetFog {
    public int getFog(String city) {
        String result = new GetWeather().dumpResults(city);
        String[] conv = result.split(",");
        String out = conv[13].replace("\"", "").replace("visibility:", "").replace(",", "");
        String correct = out.replace("000", "");
        return Integer.parseInt(correct);
    }
}
