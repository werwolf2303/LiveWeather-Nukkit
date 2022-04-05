package com.liveweather.check;

import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import com.liveweather.commandline.LWLogging;
import com.liveweather.storage.Configuring;

import java.io.File;

public class Performance {
    public boolean enoughPower() {
        if(new File(new Configuring().config).exists()) {
            if(new Configuring().getConfig("RunUnsupported").equals("true")) {
                new LWLogging().normal("Not enough power!! The Server runs maybe slow");
                return true;
            }else{
                return false;
            }
        }else {
            if (Runtime.getRuntime().availableProcessors() > 4) {
                return true;
            } else {
                return false;
            }
        }
    }
}
