package com.liveweather.location;

import com.liveweather.client.Client;

public class Tracker {
    public String getCity(String ip) {
        Client client = new Client();
        String res = client.get("http://ip-api.com/json/"+ip).replace("{", "").replace("}", "");
        String[] conv1 = res.split(",");
        return conv1[5].replace("\"city\": ", "").replace("\"", "").replace("city:", "");
    }
}
