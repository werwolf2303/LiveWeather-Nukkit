package com.liveweather.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class OPGetQuota extends Command {

    public OPGetQuota() {
        super("getquota", "getquota", "liveweather");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return false;
    }
}
