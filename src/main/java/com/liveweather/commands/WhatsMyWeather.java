package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.api.GetWeather;
import com.liveweather.check.Local;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.location.Tracker;
import com.liveweather.storage.PlayerConfigs3;
import com.liveweather.storage.YAMLConfig;

public class WhatsMyWeather extends Command {

    public WhatsMyWeather(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            new LWLogging().debugging("Response from player handler is: " + new PlayerConfigs3().getCity(p.getName()));
            if(!new YAMLConfig().read("autofindplayercity").toLowerCase().equals("true")) {
                if (new PlayerConfigs3().hasEntered(p.getName())) {
                    p.sendMessage(new Language().get("liveweather.commands.whatsmyweather.noautofind.current") + new GetWeather().getWeather(new PlayerConfigs3().getCity(p.getName())));
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
