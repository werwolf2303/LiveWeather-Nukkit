package com.liveweather.Simulator;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.SimpleCommandMap;

import java.util.List;

public class CommandMap extends SimpleCommandMap {
    public CommandMap() {
        super();
    }

    @Override
    public boolean register(String fallbackPrefix, Command command) {
        return Console.com.add(command);
    }
}
