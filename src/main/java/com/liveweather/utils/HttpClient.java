package com.liveweather.utils;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpClient {
    public static InputStream getStream(String url, @Nullable Map<String, Object> parameters) throws IOException {
        URL urlobject = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) urlobject.openConnection();
        if(parameters != null) {
            for(String key : parameters.keySet()) {
                urlConnection.setRequestProperty(key, parameters.get(key).toString());
            }
        }
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

    public static String getString(String url, Map<String, Object> parameters) throws IOException {
        return new BufferedReader(
                new InputStreamReader(getStream(url, parameters), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static String getString(String url) throws IOException {
        return new BufferedReader(
                new InputStreamReader(getStream(url, null), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
