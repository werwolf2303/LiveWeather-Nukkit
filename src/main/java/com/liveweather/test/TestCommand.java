package com.liveweather.test;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.api.SetWeather;

public class TestCommand extends Command {
    public TestCommand(String name, String description) {
        super(name, description);
    }
    Boolean raining = false;
    String playername = "";
    int raintime = 0;
    public void setRainTime(int rainTime) {
        raintime = rainTime;
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            new SetWeather().setRaining(p);
        }
        return false;
    }
}
