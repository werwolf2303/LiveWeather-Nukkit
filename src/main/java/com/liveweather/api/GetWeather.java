package com.liveweather.api;

import com.liveweather.check.APIKey;
import com.liveweather.commandline.LWLogging;
import com.liveweather.instances.InstanceManager;
import com.liveweather.language.Language;
import com.liveweather.report.Report;
import com.liveweather.storage.LWConfig;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetWeather implements GW {
    public static String apikey = "aaf3cf6e879797e568dd4014d4a694e6";
    public boolean isValid(String city) {
        return getWeather(city).equals("InvalidCity");
    }
    public String dumpResults(String city) {
        try {
            String lon = getLon(city);
            String lat = getLat(city);
            if (!new LWConfig().read("apikey").equals("YOUR_API_KEY")) {
                if (new APIKey().isValid(new LWConfig().read("apikey"))) {
                    apikey = new LWConfig().read("apikey");
                } else {
                    new LWLogging().critical(new Language().get("liveweather.api.apikeynotvalid"));
                    new LWConfig().delete("apikey");
                    new LWConfig().write("apikey", "YOUR_API_KEY");
                    return "InvalidAPIKey";
                }
            }else{
                if(!new APIKey().isValid(apikey)) {
                    new Report().create("!!IMPORTANT!!", "ApiKey invalid");
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
            } catch (IOException ignored) {

            }
            return "";
        } catch (Exception e) {
            InstanceManager.getLogger().throwable(e);
        }
        return "";
    }
    @SuppressWarnings("DuplicateBranchesInSwitch")
    public String getWeather(String city) {
        try {
            String lon = getLon(city);
            String lat = getLat(city);
            if(!new LWConfig().read("apikey").equals("YOUR_API_KEY")) {
                if(new APIKey().isValid(new LWConfig().read("apikey"))) {
                    apikey = new LWConfig().read("apikey");
                }else{
                    new LWLogging().critical(new Language().get("liveweather.api.apikeynotvalid"));
                    new LWConfig().delete("apikey");
                    new LWConfig().write("apikey", "YOUR_API_KEY");
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
                    switch(out) {
                        case "Clouds":
                            return "Clear";
                        case "Thunderstorm":
                            return "Thunderstorm";
                        case "Drizzle":
                            return "Drizzle";
                        case "Rain":
                            return "Rain";
                        case "Snow":
                            return "Snow";
                        case "Clear":
                            return "Clear";
                        case "Mist":
                            return "Clear";
                        default:
                            InstanceManager.getLogger().critical(new Language().get("liveweather.api.unhandled") + out);
                            return "Clear";
                    }
                }
            } catch (IOException ignored) {

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
            for(String s : lon) {
                if(s.contains("lon")) {
                    return s.replace("\"lon\":", "");
                }
            }
            return "NotFound";
        }catch (IOException ignored) {

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
            for(String s : lon) {
                if(s.contains("lat")) {
                    return s.replace("\"lat\":", "");
                }
            }
            return "NotFound";
        }catch (IOException ignored) {

        }
        return "";
    }
}
