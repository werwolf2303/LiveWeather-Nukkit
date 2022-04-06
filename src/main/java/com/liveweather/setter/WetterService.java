package com.liveweather.setter;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLoginEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.network.protocol.LevelEventPacket;
import com.liveweather.api.GetWeather;
import com.liveweather.language.Language;
import com.liveweather.storage.Options;
import com.liveweather.storage.PlayerConfigs;
import com.liveweather.storage.PlayerConfigs2;
import ru.nukkitx.forms.elements.CustomForm;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class WetterService implements Listener {
    @EventHandler
    public void playerEntered(PlayerJoinEvent event) {
        if(!new Options().getConfig("autofindplayercity").toLowerCase().equals("true")) {
            if (!new PlayerConfigs2().hasEntered(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(new Language().get("liveweather.service.entermessage"));
            }
        }else{
            event.getPlayer().sendMessage(new Language().get("liveweather.server.tracker.message"));
        }
    }
}
