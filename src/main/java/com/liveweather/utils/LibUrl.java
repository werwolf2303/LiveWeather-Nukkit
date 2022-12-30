package com.liveweather.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class LibUrl {
    public String get(String url) {
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }catch (IOException ex) {
            return "";
        }
    }
    /**
     Usage:
     @param url url to send
     @param data "tosend,value"
     */
    public String post(String url, String[] data) {
        try {
            HttpPost request = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<>(2);
            for(String s : data) {
                params.add(new BasicNameValuePair(s.split(",")[0], s.split(",")[1]));
            }
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }catch (IOException ex) {
            return "";
        }
    }
    public static class specialPost {
        private HttpPost request = new HttpPost("gay");
        public void post(String url, String[] data) {
            try {
                request = new HttpPost(url);
                List<NameValuePair> params = new ArrayList<>(2);
                for(String s : data) {
                    params.add(new BasicNameValuePair(s.split(",")[0], s.split(",")[1]));
                }
                request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            }catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
        public HttpPost getMethod() {
            return request;
        }
        public String execute() {
            try {
                HttpClient client = HttpClients.createDefault();
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }catch (IOException io) {
                io.printStackTrace();
                return "";
            }
        }
    }
}
