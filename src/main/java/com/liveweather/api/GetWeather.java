package com.liveweather.api;

import com.liveweather.check.APIKey;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.storage.YAMLConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
    public String dumpResults(String city) {
        try {
            String lon = getLon(city);
            String lat = getLat(city);
            if (!new YAMLConfig().read("apikey").equals("YOUR_API_KEY")) {
                if (new APIKey().isValid(new YAMLConfig().read("apikey"))) {
                    apikey = new YAMLConfig().read("apikey");
                } else {
                    new LWLogging().critical(new Language().get("liveweather.api.apikeynotvalid"));
                    new YAMLConfig().delete("apikey");
                    new YAMLConfig().write("apikey", "YOUR_API_KEY");
                    return "InvalidAPIKey";
                }
            }
            String result;
            String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apikey;
            try {
                HttpGet request = new HttpGet(url);
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                return result;
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                return "InvalidCity";
            } catch (IOException e) {

            }
            return "";
        } catch (Exception e) {

        }
        return "";
    }
    public String getWeather(String city) {
        try {
            String lon = getLon(city);
            String lat = getLat(city);
            if(!new YAMLConfig().read("apikey").equals("YOUR_API_KEY")) {
                if(new APIKey().isValid(new YAMLConfig().read("apikey"))) {
                    apikey = new YAMLConfig().read("apikey");
                }else{
                    new LWLogging().critical(new Language().get("liveweather.api.apikeynotvalid"));
                    new YAMLConfig().delete("apikey");
                    new YAMLConfig().write("apikey", "YOUR_API_KEY");
                    return "InvalidAPIKey";
                }
            }
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
                                                new LWLogging().critical(new Language().get("liveweather.api.unhandled") + out);
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
            String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + l + "&limit=1&appid=" + apikey;
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
            String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + l + "&limit=1&appid=" + apikey;
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
