package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.PublicValues;

public class DebugGetWeather extends Command {
    public DebugGetWeather() {
        super("lwgetweather", "Gets the current weather from the provider. DEBUG COMMAND");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId()) && PublicValues.playerStorageProvider.didAccept(player.getUniqueId())) {
                try {
                    Float[] latLon = PublicValues.playerStorageProvider.getSettingOfPlayer(player.getUniqueId());
                    player.sendMessage("Current weather: " + PublicValues.weatherDataProvider.getWeather(
                            latLon[0],
                            latLon[1],
                            player
                    ).toString());
                    return true;
                } catch (Exception e) {
                    player.sendMessage("Error: " + e.getMessage());
                }
            }else {
                player.sendMessage("You didn't enter any location or you didn't accept the tracking agreement");
            }
        }
        return false;
    }
}
