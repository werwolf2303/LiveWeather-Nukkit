package com.liveweather.storage;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import com.liveweather.commandline.LWLogging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class YAMLConfig {
    String config = Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "options.yml";
    File conf_emotes = new File(config);
    public Config oConfig = new Config();
    public YAMLConfig() {
        if(exist()) {
            oConfig = new Config(conf_emotes);
        }
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
    public Boolean readBoolean(String key) {
        return oConfig.getBoolean(key);
    }
    public void write(String key, String value) {
        if(exist()) {
            oConfig.set(key, value);
            oConfig.save();
        }else{
            new LWLogging().debugging("Failed to Write config not existing");
        }
    }
    public String writeReturn(String key, String value) {
        if(exist()) {
            oConfig.set(key, value);
            oConfig.save();
            return oConfig.getString(key);
        }
        return null;
    }
}
