package com.liveweather.events;

import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.instances.InstanceManager;
import com.liveweather.storage.PlayerConfig;
import com.liveweather.storage.YAMLConfig;
import com.liveweather.storage.Zippie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class onRun {
    String pluginlocation = InstanceManager.getServer().getPluginPath() + "/LiveWeather";
    File pluginloc = new File(pluginlocation);
    public onRun() {
        if(!pluginloc.exists()) {
            new LWLogging().normal("init... !FIRST RUN! This can take up to 2 minutes");
        }else{
            new LWLogging().normal("Init...");
        }
        if (!new File(pluginlocation).exists()) {
            new File(pluginlocation).mkdir();
        }
        new PlayerConfig();
        if (!new File(InstanceManager.getServer().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "extensions").exists()) {
            new File(InstanceManager.getServer().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "extensions").mkdir();
        }
        if (!new File(new YAMLConfig().getLocation()).exists()) {
            new YAMLConfig().create();
            new YAMLConfig().write("apikey", "YOUR_API_KEY");
            new YAMLConfig().write("autofindplayercity", "false");
            new YAMLConfig().write("language", "en");
            new YAMLConfig().write("permissions", "false");
        }
        java.util.logging.Logger.getLogger("org.apache.http.conn.util.PublicSuffixMatcherLoader").setLevel(java.util.logging.Level.OFF);
        if (!new File(InstanceManager.getServer().getFilePath() + "plugins/FormAPI.jar").exists()) {
            Zippie.extractZIP(InstanceManager.getServer().getFilePath() + "/plugins/" + "LiveWeather-Nukkit.jar", InstanceManager.getServer().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile");
            File source = new File(InstanceManager.getServer().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/FormAPI.jar");
            File dest = new File(InstanceManager.getServer().getFilePath() + "/plugins/FormAPI.jar");
            try {
                Files.copy(source.toPath(), dest.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InstanceManager.getServer().getPluginManager().loadPlugin(new File(InstanceManager.getServer().getFilePath() + "/plugins/FormAPI.jar"));
        }
    }
}
