package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import com.liveweather.Cache;
import com.liveweather.api.SetWeather;

public class SimulateWeather extends Command {
    public SimulateWeather(String name) {
        super(name);
        super.addCommandParameters("weathertype", new CommandParameter[]{new CommandParameter("Rain"), new CommandParameter("Thunder"), new CommandParameter("Clear")});
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        boolean skip = false;
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            for(Player player : Cache.ignorePlayer) {
                if(p.getName().equals(player.getName())) {
                    skip = true;
                }
            }
            if(!skip) {
                Cache.ignorePlayer.add(p);
            }
            switch (strings[0]) {
                case "Rain":
                    new SetWeather().setRaining(p);
                case "Thunder":
                    new SetWeather().setThundering(p);
                case "Clear":
                    new SetWeather().setClear(p);
            }
            p.sendMessage("Setting weather to: " + strings[0]);
        }
        return false;
    }
}
