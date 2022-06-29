package com.liveweather.storage;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import com.liveweather.commandline.LWLogging;

import java.io.File;
import java.io.IOException;

public class PlayerYAMLConfig {
    String config = Server.getInstance().getPluginPath().replace("\\", "/") + "LiveWeather" + "/PlayerCity/";
    File conf_emotes = new File(config);
    public Config oConfig;
    public PlayerYAMLConfig(String player) {
        if(exist()) {
            oConfig = new Config(conf_emotes);
        }
        config = config + player + ".yml";
    }
    boolean exist(){
        return conf_emotes.exists();
    }
    public String getLocation() {
        return config;
    }
    public void create() {
        try {
            conf_emotes.createNewFile();
        } catch (IOException e) {
        }
    }
    public void delete(String key) {
        if(exist()) {
            oConfig.remove(key);
            oConfig.save();
        }
    }
    public String read(String key) {
        return oConfig.getString(key);
    }
    public void write(String key, String value) {
        if(!exist()) {
            create();
        }
        oConfig.set(key, value);
        oConfig.save();
    }
}
