package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.storage.PlayerConfigs;

public class CityDelete extends Command {
    public CityDelete(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(new PlayerConfigs().hasPlayerEnteredCityName(p.getName())) {
                new PlayerConfigs().deleteConfig(p.getName());
                p.sendMessage("[LiveWeather] §bDeleted city");
            }else{
                p.sendMessage("[LiveWeather] §cYou dont entered your city");
            }
        }
        return false;
    }
}
