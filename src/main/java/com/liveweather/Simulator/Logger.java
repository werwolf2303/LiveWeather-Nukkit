package com.liveweather.Simulator;

import cn.nukkit.utils.MainLogger;
import com.liveweather.instances.InstanceManager;

public class Logger extends MainLogger {

    @Override
    public void emergency(String message) {
        InstanceManager.getDebugLogger().fatal(message);
    }

    @Override
    public void alert(String message) {
        InstanceManager.getDebugLogger().custom("[LiveWeather::Alert] " + message);
    }

    @Override
    public void critical(String message) {
        InstanceManager.getDebugLogger().critical(message);
    }

    @Override
    public void error(String message) {
        InstanceManager.getDebugLogger().error(message);
    }

    @Override
    public void warning(String message) {
        InstanceManager.getDebugLogger().warning(message);
    }

    @Override
    public void notice(String message) {
        InstanceManager.getDebugLogger().custom("[LiveWeather::Notice] " + message);
    }

    @Override
    public void info(String message) {
        InstanceManager.getDebugLogger().normal(message);
    }

    @Override
    public void debug(String message) {
        InstanceManager.getDebugLogger().debugging(message);
    }

}
