package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.utils.FormUtils;

public class LocationChange extends Command {
    public LocationChange(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player player = (Player) commandSender;
            FormUtils.openLocationSelectForm(player);
            return true;
        }
        return false;
    }
}
