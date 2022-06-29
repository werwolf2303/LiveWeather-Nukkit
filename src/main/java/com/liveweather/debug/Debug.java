package com.liveweather.debug;

import cn.nukkit.Player;
import com.liveweather.storage.Configuring;
import com.liveweather.storage.YAMLConfig;

import java.io.File;

public class Debug {
    public Debug(Player p) {
        String apikey = new YAMLConfig().read("apikey");
        String language = new YAMLConfig().read("language");
        boolean rununsupported = new File(new Configuring().config).exists();
        String iplocation = new YAMLConfig().read("autofindplayercity");
        if(apikey.equals("YOUR_API_KEY")) {
            apikey = "Plugin Default";
        }
        p.sendMessage("APIKey is: " + apikey + "\n" + "Language is: " + language + "\n" + "EnoughPerformance: " + rununsupported + "\n" + "AutoFindLocation: " + iplocation);
    }
}
