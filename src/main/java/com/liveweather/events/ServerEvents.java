package com.liveweather.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.ServerStopEvent;
import com.liveweather.storage.LWSave;

@SuppressWarnings("unused")
public class ServerEvents implements Listener {
    @EventHandler
    public void serverStop(ServerStopEvent event) {
        new LWSave().write("Reload", "false");
    }
}
