package com.liveweather.Simulator;

import cn.nukkit.Server;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.Plugin;

public class PluginManager extends cn.nukkit.plugin.PluginManager {
    public PluginManager(Server server, SimpleCommandMap commandMap) {
        super(server, commandMap);
    }

    @Override
    public Plugin loadPlugin(String path) {
        return null;
    }

    @Override
    public void disablePlugin(Plugin plugin) {

    }

    @Override
    public boolean isPluginEnabled(Plugin plugin) {
        return true;
    }

    @Override
    public void registerEvents(Listener listener, Plugin plugin) {

    }
}
