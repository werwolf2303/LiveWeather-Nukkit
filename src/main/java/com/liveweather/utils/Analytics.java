package com.liveweather.utils;

import com.liveweather.GlobalValues;
import com.liveweather.Initiator;
import com.liveweather.instances.InstanceManager;

public class Analytics {
    final String url = "http://werwolf2303.de/analytics.php";
    //Only sends usage of the plugin so that I know if I should invest my time in it
    //Data it sends
    //
    // -Version of the plugin
    // -Server name
    //
    public void send() {
        if(GlobalValues.serverdebug) {
            return;
        }
        new LibUrl().get(url + "?v=" + Initiator.plugin.getDescription().getVersion() + "&sn=" + InstanceManager.getServer().getName() + "&d=" + "false");
        InstanceManager.getLogger().normal("Send analytics data! Dont worry only the version of the plugin and the server name was send");
    }
}
