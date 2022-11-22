package com.liveweather.Simulator;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.instances.InstanceManager;

public class PlayerCommand extends Command {
    public PlayerCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        System.out.println("Players online: " + InstanceManager.getServer().getOnlinePlayers().size());
        for(cn.nukkit.Player p : InstanceManager.getServer().getOnlinePlayers().values()) {
            System.out.println(p);
        }
        return false;
    }
}
