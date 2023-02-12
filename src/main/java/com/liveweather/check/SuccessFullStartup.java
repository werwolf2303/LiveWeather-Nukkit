package com.liveweather.check;

import com.liveweather.api.GetWeather;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.report.Report;
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
        if(new LWConfig().read("apikey").equals("YOUR_API_KEY")) {
            if(!new APIKey().isValid(GetWeather.apikey)) {
                new LWLogging().error("APIKey is not valid anymore! Please enter one manually!");
                new Report().create("APIKey failed", "APIKey is not valid anymore");
                wrong = true;
            }
        }
        if(wrong) {
            new LWLogging().critical("Critical errors occurred, cant continue");
            new PluginAPI().disableLiveWeather();
        }
    }
}
