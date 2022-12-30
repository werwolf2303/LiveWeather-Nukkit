package com.liveweather.simulator;

import cn.nukkit.command.Command;
import cn.nukkit.command.SimpleCommandMap;

public class CommandMap extends SimpleCommandMap {
    public CommandMap() {
        super();
    }

    @Override
    public boolean register(String fallbackPrefix, Command command) {
        return Console.com.add(command);
    }
}
