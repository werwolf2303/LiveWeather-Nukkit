package com.liveweather.server;

import cn.nukkit.Server;
import com.liveweather.instances.InstanceManager;

public class Utils {
    public String getIndexAsString() {
        return InstanceManager.getServer().getPluginPath() + "/LiveWeather/jarfile/html/index.html";
    }
}
