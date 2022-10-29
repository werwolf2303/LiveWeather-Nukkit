package com.liveweather.language;

import com.liveweather.GlobalValues;
import com.liveweather.commandline.LWLogging;

import com.liveweather.instances.InstanceManager;
import com.liveweather.storage.LWConfig;
import com.liveweather.storage.LWSave;
import com.liveweather.utils.PluginAPI;
import com.liveweather.utils.Resources;

public class Language {
    String location = "lang/";
    String language = new LWConfig().read("language");
    public String get(String obj) {
        if(GlobalValues.langnotsupported) {
            language = "en";
        }
        String toreturn = "";
        try {
            for (String data : new Resources().read(location + language + ".cfg").split("\n")) {
                if (data.contains(obj)) {
                    toreturn = data.replace(obj + ":", "");
                    break;
                }
            }
            if (toreturn.equals("")) {
                new LWLogging().error("Language error");
                return obj;
            } else {
                return toreturn;
            }
        }catch (IllegalArgumentException a) {
            GlobalValues.langnotsupported = true;
            if(language.equals("en")) {
                new LWLogging().fatal("Language failure");
                new PluginAPI().disableLiveWeather();
            }else {
                new LWLogging().error("Language \"" + language + "\" not supported revert to default: \"en\"");
            }
            return obj;
        }
    }
}
