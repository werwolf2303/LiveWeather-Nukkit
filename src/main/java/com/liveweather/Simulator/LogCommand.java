package com.liveweather.simulator;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class LogCommand extends Command {
    public LogCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        String cache = "";
        for(String s2 : strings) {
            if(cache.equals("")) {
                cache = s2;
            }else{
                //noinspection StringConcatenationInLoop
                cache = cache + " " + s2;
            }
        }
        new SimulatorLogger().info(cache);
        return false;
    }
}
