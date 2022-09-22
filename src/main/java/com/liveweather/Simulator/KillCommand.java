package com.liveweather.Simulator;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class KillCommand extends Command {
    public KillCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        System.exit(0);
        return false;
    }
}
