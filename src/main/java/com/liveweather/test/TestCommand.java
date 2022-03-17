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
import com.liveweather.storage.PlayerConfigs;
import ru.nukkitx.forms.elements.CustomForm;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class TestCommand extends Command {
    public TestCommand(String name, String description) {
        super(name, description);
        addCommandParameters("clear", new CommandParameter[] {});
        addCommandParameters("rain", new CommandParameter[] {});
        addCommandParameters("thunder", new CommandParameter[] {});
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
            try {
                if(args[0].contains("clear")) {
                    new SetWeather().setClear(p);
                }else{
                    if(args[0].contains("thunder")) {
                        new SetWeather().setThundering(p);
                    }else{
                        if(args[0].contains("rain")) {
                            new SetWeather().setRaining(p);
                        }else{
                            p.sendMessage("Invalid weather");
                        }
                    }
                }
            }catch (ArrayIndexOutOfBoundsException esc) {

            }
        }
        return false;
    }
}
