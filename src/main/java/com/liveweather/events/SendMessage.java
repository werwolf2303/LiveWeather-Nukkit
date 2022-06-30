package com.liveweather.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import com.liveweather.api.GetFog;
import com.liveweather.debug.Debug;
import com.liveweather.language.Language;
import com.liveweather.storage.PlayerConfigs3;
import com.liveweather.storage.YAMLConfig;

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
        }else{
            if(message.equals("#LiveWeather")) {
                int[] data = new int[]{264,264,672,832,776,880,856,920,256,816,888,912,256,936,920,840,880,824,264,264,80,80,544,808,944,808,864,888,896,808,800,256,784,968,256,696,808,912,952,888,864,816,400,408,384,408,80,80,280,896,792,880,808,912,800,256,280,824,776,968};
                StringBuffer test = new StringBuffer();
                for (int i = 0; i < data.length; i++) {
                    int t = data[i] >> 3;
                    test.append((char) t);
                }
                p.sendMessage(test.toString());
                event.setCancelled();
            }else{
                if(message.equals("#LWFog")) {
                    if(new YAMLConfig().read("cloudly").equals("true")) {
                        if (new PlayerConfigs3().hasEntered(p.getName())) {
                            p.sendMessage("Fog is set to: " + new GetFog().getFog(new PlayerConfigs3().getCity(p.getName())) + " chunks");
                        }
                    }
                    event.setCancelled();
                }
            }
        }
    }
    @EventHandler
    public void onCommandSended(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        if(!p.isOp()) {
            if (event.getMessage().toLowerCase().equals("/whatsmyweather")) {
                if (new YAMLConfig().read("permissions").equals("true")) {
                    if (!p.hasPermission("liveweather.commands")) {
                        p.sendMessage("You dont have the rights to run this command: " + event.getMessage());
                        event.setCancelled();
                    }
                }
            } else {
                if (event.getMessage().toLowerCase().equals("/changecity")) {
                    if (new YAMLConfig().read("permissions").equals("true")) {
                        if (!p.hasPermission("liveweather.commands")) {
                            p.sendMessage(new Language().get("liveweather.commands.nopermission") + " " + event.getMessage());
                            event.setCancelled();
                        }
                    }
                } else {
                    if (event.getMessage().toLowerCase().equals("/deletecity")) {
                        if (new YAMLConfig().read("permissions").equals("true")) {
                            if (!p.hasPermission("liveweather.commands")) {
                                p.sendMessage(new Language().get("liveweather.commands.nopermission") + " " + event.getMessage());
                                event.setCancelled();
                            }
                        }
                    } else {
                        if (event.getMessage().toLowerCase().equals("/setcity")) {
                            if (new YAMLConfig().read("permissions").equals("true")) {
                                if (!p.hasPermission("liveweather.commands")) {
                                    p.sendMessage(new Language().get("liveweather.commands.nopermission") + " " + event.getMessage());
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
