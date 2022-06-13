package com.liveweather.events;

import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.storage.Zippie;
import com.liveweather.threading.High;

import java.io.File;

public class OnStartup {
    File jarfolder = new File(Server.getInstance().getPluginPath() + "/LiveWeather/jarfile");
    public OnStartup() {
        High high = new High(this::execute);
        high.start();
    }
    public Runtime execute() {
        if(jarfolder.exists()) {
            if(!deleteDirectory(jarfolder)) {
                new LWLogging().error("Cant delete plugin jarfolder : " + "'jarfile'");
            }else{
                Zippie.extractZIP(Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather-Nukkit.jar", Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile");
            }
        }
        return null;
    }
    static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDirectory(children[i]);
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
