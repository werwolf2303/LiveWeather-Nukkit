package com.liveweather.check;


import com.liveweather.storage.LWConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class APIKey {
    public boolean isValid(String key) {
        try {
            String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + "Weinheim" + "&limit=1&appid=" + key;
            HttpGet request = new HttpGet(url);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            String[] lon = result.replace("[", "").replace("]", "").split(",");
            if(lon[1].contains("Invalid")) {
                return false;
            }else{
                return true;
            }
        }catch (IOException ioe) {
            return false;
        }
    }
    public String getAPIKey() {
        return new LWConfig().read("apikey");
    }
}
