package com.liveweather.test;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.event.level.WeatherEvent;
import cn.nukkit.level.Level;
import cn.nukkit.network.protocol.LevelEventPacket;
import com.liveweather.api.SetWeather;
import com.liveweather.commandline.LWLogging;
import com.liveweather.storage.PlayerConfigs;
import com.liveweather.storage.PlayerConfigs2;
import ru.nukkitx.forms.elements.CustomForm;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

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
            p.sendMessage("Debug is: " + p.getAddress());
        }
        return false;
    }
}
