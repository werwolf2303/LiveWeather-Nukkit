package com.liveweather.simulator;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class DumpCommand extends Command {

    public DumpCommand(String name) {
        super(name, "Dumps all available commands (Simulator Only)");
    }

    @Override
    public boolean execute(CommandSender var1, String var2, String[] var3) {
        for(Command s : Console.com) {
            System.out.println("Available: " + s + "  =>  " + s.getDescription());
        }
        return false;
    }
}
