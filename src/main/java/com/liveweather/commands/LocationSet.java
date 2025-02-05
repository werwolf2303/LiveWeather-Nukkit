package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.PublicValues;
import com.liveweather.config.ConfigValues;
import com.liveweather.utils.FormUtils;

public class LocationSet extends Command {
    public LocationSet(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player player = (Player) commandSender;
            if(PublicValues.config.getBoolean(ConfigValues.autoFind.name)) {
                FormUtils.openTrackingAgreementForm(player);
            }else {
                FormUtils.openLocationSelectForm(player);
            }
            return true;
        }
        return false;
    }
}
