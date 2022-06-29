package com.liveweather.setter;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import com.liveweather.language.Language;
import com.liveweather.storage.PlayerConfigs3;
import com.liveweather.storage.YAMLConfig;


public class WetterService implements Listener {
    @EventHandler
    public void playerEntered(PlayerJoinEvent event) {
        if(!new YAMLConfig().read("autofindplayercity").toLowerCase().equals("true")) {
            if (!new PlayerConfigs3().hasEntered(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(new Language().get("liveweather.service.entermessage"));
            }
        }else{
            event.getPlayer().sendMessage(new Language().get("liveweather.server.tracker.message"));
        }
    }
}
