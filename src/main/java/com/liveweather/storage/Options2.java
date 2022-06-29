package com.liveweather.storage;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;

import java.io.File;

@Deprecated
public class Options2 {
    public String configlocation = Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "options.cfg";
    Config config = new Config(configlocation);
    public void createConfig() {
        if(!new File(configlocation).exists()) {
            config.save();
        }
    }
    public void write(String key, String value) {
        config.set(key,value);
        config.save();
    }
    public String read(String key) {
        return config.get(key).toString();
    }
}
