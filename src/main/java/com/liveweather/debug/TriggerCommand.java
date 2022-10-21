package com.liveweather.debug;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.commandline.LWLogging;

public class TriggerCommand extends Command {

    public TriggerCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            if(strings[0].toLowerCase().contains("info")) {
                new LWLogging().normal(strings[1]);
            }
            if(strings[0].toLowerCase().contains("debugging")) {
                new LWLogging().debugging(strings[1]);
            }
            if(strings[0].toLowerCase().contains("error")) {
                new LWLogging().error(strings[1]);
            }
            if(strings[0].toLowerCase().contains("extension")) {
                new LWLogging().extension(strings[1]);
            }
            if(strings[0].toLowerCase().contains("warning")) {
                new LWLogging().warning(strings[1]);
            }
            if(strings[0].toLowerCase().contains("critical")) {
                new LWLogging().critical(strings[1]);
            }
        }
        return false;
    }
}
