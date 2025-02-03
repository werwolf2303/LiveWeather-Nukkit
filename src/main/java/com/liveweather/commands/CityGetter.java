package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.PublicValues;

import java.sql.SQLException;

public class CityGetter extends Command {

    public CityGetter(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player player = (Player) commandSender;
           // commandSender.sendMessage("[LiveWeather] " + PublicValues.language.translate("liveweather.commands.getcity.playermessage") + ": " + PublicValues.playerStorageProvider.getCityOfPlayer(player.getUniqueId()));
            return true;
        }
        commandSender.sendMessage("[LiveWeather] CommandSender needs to be a player");
        return false;
    }
}
