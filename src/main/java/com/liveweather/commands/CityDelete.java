package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.PublicValues;
import com.liveweather.ChatColors;

import static com.liveweather.MessageConstructor.constructMessage;

public class CityDelete extends Command {
    public CityDelete(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player player = (Player) commandSender;
            if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId())) {
                PublicValues.playerStorageProvider.deletePlayer(player.getUniqueId());
                player.sendMessage(PublicValues.language.translate("liveweather.commands.citydelete.success"));
            }else{
                player.sendMessage(PublicValues.language.translate("liveweather.commands.citydelete.dontentered"));
            }
        }else{
            commandSender.sendMessage(constructMessage(ChatColors.RED, PublicValues.language.translate("general.internalerror")));
        }
        return false;
    }
}
