package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.api.GetWeather;
import com.liveweather.check.Local;
import com.liveweather.language.Language;
import com.liveweather.location.Tracker;
import com.liveweather.storage.PlayerConfig;
import com.liveweather.storage.YAMLConfig;

public class WhatsMyWeather extends Command {

    public WhatsMyWeather(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player p = (Player) commandSender;
            if(!new YAMLConfig().read("autofindplayercity").toLowerCase().equals("true")) {
                if (!new YAMLConfig().read(p.getName()).equals("")) {
                    p.sendMessage(new Language().get("liveweather.commands.whatsmyweather.noautofind.current") + new GetWeather().getWeather(new YAMLConfig().read(p.getName())));
                } else {
                    p.sendMessage(new Language().get("liveweather.commands.whatsmyweather.noautofind.error"));
                }
            }else{
                if(!new Local().isLocal(p)) {
                    p.sendMessage(new Language().get("liveweather.commands.whatsmyweather.autofind.current") + new Tracker().getCity(p.getAddress()));
                }else{
                    p.sendMessage(new Language().get("liveweather.commands.whatsmyweather.autofind.error"));
                }
            }
        }else{
            commandSender.sendMessage(new Language().get("liveweather.commands.server"));
        }
        return false;
    }
}
