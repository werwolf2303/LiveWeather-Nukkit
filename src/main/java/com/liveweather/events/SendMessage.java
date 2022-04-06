package com.liveweather.events;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import com.liveweather.audio.JAudio;
import com.liveweather.commandline.LWLogging;
import com.liveweather.debug.Debug;

public class SendMessage implements Listener {
    @EventHandler
    public void onSendMessage(PlayerChatEvent event) {
        Player p = event.getPlayer();
        String message = event.getMessage();
        if(message.equals("#LWDebug")) {
            if (p.isOp()) {
                new Debug(p);
                event.setCancelled();
            }
        }
    }
}
