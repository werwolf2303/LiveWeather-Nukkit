package com.liveweather.debug;

import cn.nukkit.Player;
import com.liveweather.storage.Configuring;
import com.liveweather.storage.Options;

import java.io.File;

public class Debug {
    public Debug(Player p) {
        String apikey = new Options().getConfig("apikey");
        String language = new Options().getConfig("language");
        boolean rununsupported = new File(new Configuring().config).exists();
        String iplocation = new Options().getConfig("autofindplayercity");

        if(language.toLowerCase().contains("en")) {
            language = "Deutsch";
        }else{
            language = "English";
        }
        if(apikey.equals("YOUR_API_KEY")) {
            apikey = "Plugin Default";
        }
        p.sendMessage("APIKey is: " + apikey + "\n" + "Language is: " + language + "\n" + "EnoughPerformance: " + rununsupported + "\n" + "AutoFindLocation: " + iplocation);
    }
}
