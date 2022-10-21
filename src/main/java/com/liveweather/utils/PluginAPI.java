package com.liveweather.utils;

import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import com.liveweather.Initiator;
import com.liveweather.instances.InstanceManager;

public class PluginAPI {
    public void disableLiveWeather() {
        WorkArounds.unregisterPlugin(Initiator.plugin);
        InstanceManager.getServer().getPluginManager().disablePlugin(Initiator.plugin);
    }
}
