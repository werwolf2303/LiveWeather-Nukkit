package com.liveweather.events;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.server.ServerCommandEvent;
import com.liveweather.audio.JAudio;
import com.liveweather.commandline.LWLogging;
import com.liveweather.debug.Debug;
import com.liveweather.storage.Options;
import com.liveweather.updater.Update;

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
    @EventHandler
    public void onCommandSended(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        if(!p.isOp()) {
            if (event.getMessage().toLowerCase().equals("/whatsmyweather")) {
                if (new Options().getConfig("permissions").equals("true")) {
                    if (!p.hasPermission("liveweather.commands")) {
                        p.sendMessage("You dont have the rights to run this command: " + event.getMessage());
                        event.setCancelled();
                    }
                }
            } else {
                if (event.getMessage().toLowerCase().equals("/changecity")) {
                    if (new Options().getConfig("permissions").equals("true")) {
                        if (!p.hasPermission("liveweather.commands")) {
                            p.sendMessage("You dont have the rights to run this command: " + event.getMessage());
                            event.setCancelled();
                        }
                    }
                } else {
                    if (event.getMessage().toLowerCase().equals("/deletecity")) {
                        if (new Options().getConfig("permissions").equals("true")) {
                            if (!p.hasPermission("liveweather.commands")) {
                                p.sendMessage("You dont have the rights to run this command: " + event.getMessage());
                                event.setCancelled();
                            }
                        }
                    } else {
                        if (event.getMessage().toLowerCase().equals("/setcity")) {
                            if (new Options().getConfig("permissions").equals("true")) {
                                if (!p.hasPermission("liveweather.commands")) {
                                    p.sendMessage("You dont have the rights to run this command: " + event.getMessage());
                                    event.setCancelled();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
