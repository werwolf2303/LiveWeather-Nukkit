package com.liveweather.instances;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import com.liveweather.GlobalValues;
import com.liveweather.Simulator.Console;

public class InstanceManager {
    static com.liveweather.Simulator.Server server = null;
    static Console console = null;
    public static Server getServer() {
        if(GlobalValues.debug) {
            if(server==null) {
                server = new com.liveweather.Simulator.Server();
            }
            return server;
        }
        return Server.getInstance();
    }
    public static Console getConsole() {
        if(console!=null) {
            return console;
        }else{
            return null;
        }
    }
    public static void setConsole(Console cons) {
        console = cons;
    }
}
