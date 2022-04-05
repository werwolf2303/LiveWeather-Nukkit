package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.api.GetWeather;
import com.liveweather.storage.PlayerConfigs;
import com.liveweather.storage.PlayerConfigs2;

public class WhatsMyWeather extends Command {

    public WhatsMyWeather(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(new PlayerConfigs2().hasEntered(p.getName())) {
                p.sendMessage("[LiveWeather] Current weather is: " + new GetWeather().getWeather(new PlayerConfigs2().getCity(p.getName())));
            }else{
                p.sendMessage("[LiveWeather] Error you dont set your city\n\n Set it with /setcity");
            }
        }
        return false;
    }
}
