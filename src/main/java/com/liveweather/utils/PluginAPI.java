package com.liveweather.utils;

import com.liveweather.GlobalValues;
import com.liveweather.Initiator;
import com.liveweather.instances.InstanceManager;

public class PluginAPI {
    public void disableLiveWeather() {
        if(GlobalValues.debug) {
            InstanceManager.getDebugLogger().custom("Plugin tried to disable LiveWeather");
            return;
        }
        WorkArounds.unregisterPlugin(Initiator.plugin);
        InstanceManager.getServer().getPluginManager().disablePlugin(Initiator.plugin);
    }
}
