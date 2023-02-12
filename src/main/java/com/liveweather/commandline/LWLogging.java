package com.liveweather.commandline;

import com.liveweather.Logger;
import com.liveweather.instances.InstanceManager;
import com.liveweather.utils.PluginAPI;


@SuppressWarnings("unused")
public class LWLogging implements Logger {
    public void throwable(Throwable throwable) {
        InstanceManager.getServer().getLogger().alert("[LiveWeather::Throwable] " + throwable.getMessage());
    }
    public void fatal(String message) {
        InstanceManager.getServer().getLogger().emergency(message);
    }
    public void critical(String message) {
        InstanceManager.getServer().getLogger().error("[LiveWeather::Critical::PANIC] " + message);
        new PluginAPI().disableLiveWeather();
    }
    public void extension(String message) {
        InstanceManager.getServer().getLogger().info("[LiveWeather <Extension>] " + message);
    }
    public void warning(String message) {
        InstanceManager.getServer().getLogger().warning("[LiveWeather::Warning] " + message);
    }
    public void normal(String message) {
        InstanceManager.getServer().getLogger().info("[LiveWeather] " + message);
    }
    public void error(String message) {
        InstanceManager.getServer().getLogger().error("[LiveWeather::Error] " + message);
    }
    public void debugging(String message) {
        InstanceManager.getServer().getLogger().info("[LiveWeather::Debugging] " + message);
    }
}
