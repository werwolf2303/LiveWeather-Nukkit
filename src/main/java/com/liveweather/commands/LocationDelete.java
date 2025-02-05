package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.MessageConstructor;
import com.liveweather.PublicValues;

public class LocationDelete extends Command {
    public LocationDelete(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player player = (Player) commandSender;
            if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId())) {
                PublicValues.playerStorageProvider.deletePlayer(player.getUniqueId());
                player.sendMessage(MessageConstructor.fromTranslation(PublicValues.language.translate("liveweather.commands.locationdelete.success")));
            }else{
                player.sendMessage(MessageConstructor.fromTranslation(PublicValues.language.translate("liveweather.commands.locationdelete.didntenter")));
            }
            return true;
        }
        return false;
    }
}
