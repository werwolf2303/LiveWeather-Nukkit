package com.liveweather.server;

import cn.nukkit.Server;

public class Utils {
    public String getIndexAsString() {
        return Server.getInstance().getPluginPath() + "/LiveWeather/jarfile/html/index.html";
    }
}
