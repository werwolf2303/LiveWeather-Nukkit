package com.liveweather.test;

import com.liveweather.GlobalValues;
import com.liveweather.instances.InstanceManager;
import com.liveweather.language.Language;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        GlobalValues.debug = true;
        Language language = new Language();
        language.lang = "chs";
        System.out.println(language.get("liveweather.forms.title"));
    }
}