package com.liveweather.instances;

import cn.nukkit.Server;
import com.liveweather.GlobalValues;
import com.liveweather.Logger;
import com.liveweather.simulator.APIResponse;
import com.liveweather.simulator.Console;
import com.liveweather.api.GetWeather;
import com.liveweather.commandline.LWLogging;
import com.liveweather.debug.DebugLogger;

public class InstanceManager {
    static com.liveweather.simulator.Server server = null;
    static DebugLogger logger = null;
    static GetWeather weather = null;
    static Console console = null;
    public static Server getServer() {
        if(GlobalValues.debug) {
            if(server==null) {
                server = new com.liveweather.simulator.Server();
            }
            return server;
        }
        return Server.getInstance();
    }
    @SuppressWarnings("unused")
    public static GetWeather getWeather() {
        if(GlobalValues.debug) {
            if(weather==null) {
                weather = new APIResponse();
            }
        }
        return weather;
    }
    public static DebugLogger getDebugLogger() {
        if(logger==null) {
            logger = new DebugLogger();
        }
        return logger;
    }
    static Logger log = null;
    public static Logger getLogger() {
        if(GlobalValues.serverdebug) {
            log = new DebugLogger();
        }else{
            log = new LWLogging();
        }
        return log;
    }
    @SuppressWarnings("unused")
    public static Console getConsole() {
        if(console!=null) {
            return console;
        }else{
            return null;
        }
    }
    public static void setConsole(Console cons) {
        console = cons;
    }
}
