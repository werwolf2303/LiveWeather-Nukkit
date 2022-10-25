package com.liveweather.language;

import com.liveweather.GlobalValues;
import com.liveweather.commandline.LWLogging;

import com.liveweather.storage.LWConfig;
import com.liveweather.utils.Resources;

public class Language {
    String location = "lang/";
    String language = new LWConfig().read("language");
    public String get(String obj) {
        if(GlobalValues.langnotsupported) {
            language = "de";
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
                return obj;
            } else {
                return toreturn;
            }
        }catch (IllegalArgumentException a) {
            GlobalValues.langnotsupported = true;
            new LWLogging().error("Language \"" + language + "\" not supported revert to default: \"en\"");
            new LWLogging().normal("Lang is: " + new LWConfig().read("language"));
            return obj;
        }
    }
}
