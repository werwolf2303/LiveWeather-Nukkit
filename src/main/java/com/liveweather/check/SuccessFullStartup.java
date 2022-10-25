package com.liveweather.check;

import com.liveweather.Initiator;
import com.liveweather.commandline.LWLogging;
import com.liveweather.instances.InstanceManager;
import com.liveweather.language.Language;
import com.liveweather.storage.LWConfig;
import com.liveweather.utils.PluginAPI;


public class SuccessFullStartup {
    public SuccessFullStartup() {
        boolean wrong = false;
        try {
            if (new LWConfig().read("language").equals("")) {
                wrong = true;
                new LWLogging().critical(new Language().get("liveweather.error.misbehaviour") + " " + "language");
            }
            if (new LWConfig().read("permissions").equals("")) {
                wrong = true;
                new LWLogging().critical(new Language().get("liveweather.error.misbehaviour") + " " + "permissions");
            }
            if (new LWConfig().read("autofindplayercity").equals("")) {
                wrong = true;
                new LWLogging().critical(new Language().get("liveweather.error.misbehaviour") + " " + "autofindplayercity");
            }
            if (new LWConfig().read("apikey").equals("")) {
                wrong = true;
                new LWLogging().critical(new Language().get("liveweather.error.misbehaviour") + " " + "apikey");
            }
        }catch (NullPointerException nullpointer) {
            wrong = true;
        }
        if(wrong) {
            new LWLogging().critical("Critical errors occurred, cant continue");
            new PluginAPI().disableLiveWeather();
        }
    }
}
