package com.liveweather.storage;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class YAMLConfig {
    public String config = Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "options.yml";
    File conf_emotes = new File(config);
    Config oConfig = null;
    public YAMLConfig() {
        oConfig = new Config(conf_emotes);
    }
    public void create() {
        try {
            conf_emotes.createNewFile();
        } catch (IOException e) {
        }
    }
    public void delete(String key) {
        oConfig.remove(key);
        oConfig.save();
    }
    public String read(String key) {
        return oConfig.getString(key);
    }
    public void write(String key, String value) {
        oConfig.set(key,value);
        oConfig.save();
    }
}
