package com.liveweather.check;

import cn.nukkit.Server;
import com.liveweather.Initiator;
import com.liveweather.commandline.LWLogging;
import com.liveweather.storage.Options2;

public class SuccessFullStartup {
    public SuccessFullStartup() {
        boolean wrong = false;
        try {
            if (new Options2().read("language").equals("")) {
                wrong = true;
                new LWLogging().critical("Plugin misbehaviour -> Config write error :: language");
            }
            if (new Options2().read("permissions").equals("")) {
                wrong = true;
                new LWLogging().critical("Plugin misbehaviour -> Config write error :: permissions");
            }
            if (new Options2().read("autofindplayercity").equals("")) {
                wrong = true;
                new LWLogging().critical("Plugin misbehaviour -> Config write error :: autofindplayercity");
            }
            if (new Options2().read("apikey").equals("")) {
                wrong = true;
                new LWLogging().critical("Plugin misbehaviour -> Config write error :: apikey");
            }
        }catch (NullPointerException nullpointer) {
            wrong = true;
        }
        if(wrong) {
            new LWLogging().critical("Critical errors occurred, cant continue");
            Server.getInstance().getPluginManager().disablePlugin(Initiator.plugin);
        }
    }
}
