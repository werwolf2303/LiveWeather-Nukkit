package com.liveweather.Simulator;

import cn.nukkit.Server;
import com.liveweather.GlobalValues;
import com.liveweather.Initiator;
import com.liveweather.api.GetWeather;
import com.liveweather.commandline.LWLogging;
import com.liveweather.instances.InstanceManager;

import java.io.File;

public class Simulate {
    public static void main(String[] args) {
        if(!new File("nukkit.jar").exists()) {
            System.err.println("Cant run Simulator! Cant find modified version of nukkit");
            return;
        }
        GlobalValues.debug = true;
        if(!new File("DebugEnv").exists()) {
            new File("DebugEnv").mkdir();
            new File("DebugEnv", "plugins").mkdir();
            new File("DebugEnv", "worlds").mkdir();
        }
        InstanceManager.getServer().addOnlinePlayer(new Player());
        System.out.println("Simulator Mode: Console has only plugin commands");
        new Initiator().onLoad();
        new Initiator().onEnable();
        InstanceManager.getServer().getCommandMap().register("help", new HelpCommand("help"));
        InstanceManager.getServer().getCommandMap().register("help", new DumpCommand("dump"));
        InstanceManager.getServer().getCommandMap().register("help", new PlayerCommand("players"));
        new Console().init();
    }
}
