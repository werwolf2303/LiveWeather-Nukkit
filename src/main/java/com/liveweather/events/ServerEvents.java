package com.liveweather.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.server.ServerStopEvent;
import com.liveweather.storage.LWSave;

public class ServerEvents implements Listener {
    @EventHandler
    public void serverStop(ServerStopEvent event) {
        new LWSave().write("Reload", "false");
    }
}
