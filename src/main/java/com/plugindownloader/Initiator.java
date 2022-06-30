package com.plugindownloader;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;

import java.io.File;

public class Initiator extends PluginBase {
    @Override
    public void onLoad() {
        if(!new File(Server.getInstance().getPluginPath().replace("\\", "/") + "LiveWeather-Nukkit.jar").exists()) {
            Server.getInstance().getLogger().info("Start downloading liveweather plugin");
            boolean suc = Downloader.download("https://github.com/Werwolf2303/LiveWeather-Nukkit/releases/latest/download/LiveWeather-Nukkit.jar", Server.getInstance().getPluginPath());
            if(suc) {
                Server.getInstance().getLogger().info("Download finished! You can now remove PluginDownloader");
            }
        }else{
            Server.getInstance().getLogger().info("Please remove LiveWeather plugin first");
        }
    }
}
