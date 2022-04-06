package com.liveweather.client;

import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Client {
    public String get(String url) {
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        }catch (IOException ioException) {
            new LWLogging().critical(new Language().get("liveweather.client.error"));
            return null;
        }
    }
}
