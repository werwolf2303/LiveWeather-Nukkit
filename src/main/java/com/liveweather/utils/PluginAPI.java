package com.liveweather.utils;

import cn.nukkit.plugin.Plugin;
import com.liveweather.commandline.LWLogging;
import com.liveweather.instances.InstanceManager;

import java.io.File;
import java.util.Map;

public class PluginAPI {
    public void delete(String name) {
        boolean found = false;
        for(Plugin pl : InstanceManager.getServer().getPluginManager().getPlugins().values()) {
            if(pl.getName().toLowerCase().contains(name.toLowerCase())) {
                InstanceManager.getServer().getPluginManager().disablePlugin(pl);
                new File(InstanceManager.getServer().getPluginPath().replace("\\", "/") + pl.getName() + ".jar").delete();
                break;
            }
        }
        if(found) {
            InstanceManager.getServer().reload();
        }else{
            new LWLogging().error("Cant find plugin with name: " + name);
        }
    }
}
