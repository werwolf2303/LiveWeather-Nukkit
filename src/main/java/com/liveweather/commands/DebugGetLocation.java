package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.PublicValues;

public class DebugGetLocation extends Command {

    public DebugGetLocation() {
        super("lwgetlocation", "Get the current location that you entered. DEBUG COMMAND");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId()) && PublicValues.playerStorageProvider.didAccept(player.getUniqueId())) {
                Float[] latLon = PublicValues.playerStorageProvider.getSettingOfPlayer(player.getUniqueId());
                player.sendMessage("Lat: " + latLon[0] + "\nLon: " + latLon[1]);
            }else {
                player.sendMessage("You didn't enter any location or you didn't accept the tracking agreement");
            }
            return true;
        }
        return false;
    }
}
