package com.liveweather.check;

import com.liveweather.GlobalValues;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.storage.Configuring;

import java.io.File;

public class Performance {
    public boolean enoughPower() {
        if(GlobalValues.debug) {
            return true;
        }
        if(new File(new Configuring().config).exists()) {
            if(new Configuring().getConfig("RunUnsupported").equals("true")) {
                new LWLogging().normal(new Language().get("liveweather.performance.warning"));
                return true;
            }else{
                return false;
            }
        }else {
            return Runtime.getRuntime().availableProcessors() > 4;
        }
    }
}
