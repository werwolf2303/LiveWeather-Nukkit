package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.language.Language;
import com.liveweather.storage.PlayerConfig;
import com.liveweather.storage.PlayerConfigs3;

public class CityDelete extends Command {
    public CityDelete(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(new PlayerConfig().hasEntered(p.getName())) {
                new PlayerConfig().deletePlayer(p.getName());
                p.sendMessage(new Language().get("liveweather.commands.citydelete.success"));
            }else{
                p.sendMessage(new Language().get("liveweather.commands.citydelete.dontentered"));
            }
        }else{
            commandSender.sendMessage(new Language().get("liveweather.commands.server"));
        }
        return false;
    }
}
