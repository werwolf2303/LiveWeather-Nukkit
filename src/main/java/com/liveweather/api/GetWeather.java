package com.liveweather.api;

import cn.nukkit.Server;
import cn.nukkit.utils.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;

public class GetWeather {
    private String apikey = "aaf3cf6e879797e568dd4014d4a694e6";
    @Deprecated
    public boolean isValid(String city) {
        if(getWeather(city).equals("InvalidCity")) {
            return false;
        }else{
            return true;
        }
    }
    public String getWeather(String city) {
        try {
            String lon = getLon(city);
            String lat = getLat(city);
            String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apikey;
            try {
                HttpGet request = new HttpGet(url);
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                String[] conv = result.split(",");
                String out = conv[3].replace("\"", "").replace(":", "").replace("main", "");
                if (conv[3].contains("main")) {
                    if (out.contains("Clouds")) {
                        return "Clear";
                    } else {
                        if (out.contains("Thunderstorm")) {
                            return "Thunderstorm";
                        } else {
                            if (out.contains("Drizzle")) {
                                return "Drizzle";
                            } else {
                                if (out.contains("Rain")) {
                                    return "Rain";
                                } else {
                                    if (out.contains("Snow")) {
                                        return "Snow";
                                    } else {
                                        if (out.contains("Clear")) {
                                            return "Clear";
                                        } else {
                                            if (out.contains("Mist")) {
                                                return "Clear";
                                            } else {
                                                Server.getInstance().getLogger().alert("Exception LiveWeather: Unhandled Weather : " + out);
                                                return "Clear";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException ioe) {

            }
        }catch (ArrayIndexOutOfBoundsException aioobe) {
            return "InvalidCity";
        }
        return "";
    }
    public String getLon(String l) {
        try {
            String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + l + "&limit=1&appid=aaf3cf6e879797e568dd4014d4a694e6";
            HttpGet request = new HttpGet(url);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            String[] lon = result.replace("[", "").replace("]", "").split(",");
            if(lon[4].contains("lon")) {
                return lon[4].replace("\"lon\":", "");
            }else{
                return "NotFound";
            }
        }catch (IOException ioe) {

        }
        return "";
    }
    public String getLat(String l) {
        try {
            String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + l + "&limit=1&appid=aaf3cf6e879797e568dd4014d4a694e6";
            HttpGet request = new HttpGet(url);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            String[] lon = result.replace("[", "").replace("]", "").split(",");
            if(lon[3].contains("lat")) {
                return lon[3].replace("\"lat\":", "");
            }else{
                return "NotFound";
            }
        }catch (IOException ioe) {

        }
        return "";
    }
}
