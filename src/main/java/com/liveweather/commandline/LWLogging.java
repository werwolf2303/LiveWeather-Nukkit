package com.liveweather.commandline;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginClassLoader;
import cn.nukkit.plugin.PluginLoader;
import com.liveweather.Initiator;
import com.liveweather.check.CheckIntelliJ;
import com.liveweather.instances.InstanceManager;
import com.liveweather.utils.PluginAPI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LWLogging {
    boolean debug = new CheckIntelliJ().check();
    String location = "";
    public LWLogging() {
        if(!debug) {
            location = InstanceManager.getServer().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/sounds/";
        }
    }
    public void critical(String message) {
        if(!debug) {
            InstanceManager.getServer().getLogger().error("[LiveWeather::Critical::PANIC] " + message);
            new PluginAPI().disableLiveWeather();
        }else{
            System.err.println("[LiveWeather::Crtitical] " + message);
        }
    }
    public void extension(String message) {
        if(!debug) {
            InstanceManager.getServer().getLogger().info("[LiveWeather <Extension>] " + message);
        }
    }
    public void warning(String message) {
        if(!debug) {
            InstanceManager.getServer().getLogger().warning("[LiveWeather::Warning] " + message);
        }else{
            System.out.println("[LiveWeather::Warning] " + message);
        }
    }
    public void normal(String message) {
        if(!debug) {
            InstanceManager.getServer().getLogger().info("[LiveWeather] " + message);
        }else{
            System.out.println("[LiveWeather] " + message);
        }
    }
    public void error(String message) {
        if(!debug) {
            InstanceManager.getServer().getLogger().error("[LiveWeather::Error] " + message);
        }else{
            System.err.println("[LiveWeather::Error] " + message);
        }
    }
    public void debugging(String message) {
        if(!debug) {
            InstanceManager.getServer().getLogger().info("[LiveWeather::Debugging] " + message);
        }else{
            System.out.println("[LiveWeather::Debugging] " + message);
        }
    }
}
