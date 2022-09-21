package com.liveweather.Simulator;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class HelpCommand extends Command {
    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        System.out.println("Type 'dump' to see all available commands");
        return false;
    }
}
