package com.liveweather.client;

import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.report.Report;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Client {
    public String get(String url) {
        return "";
    }
}
