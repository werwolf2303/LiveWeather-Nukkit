package com.liveweather.test;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.event.level.WeatherEvent;
import cn.nukkit.level.Level;
import cn.nukkit.network.protocol.LevelEventPacket;
import com.liveweather.Initiator;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class TestCommand extends Command {
    public TestCommand(String name, String description) {
        super(name, description);
    }
    Boolean raining = false;
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            WeatherChangeEvent event = new WeatherChangeEvent(p.getLevel(), raining);
            Server.getInstance().getPluginManager().callEvent(event);
            LevelEventPacket pk = new LevelEventPacket();
            if (raining) {
                pk.evid = 3001;
                int time = ThreadLocalRandom.current().nextInt(12000) + 12000;
                pk.data = time;
                p.getLevel().setRainTime(time);
            } else {
                pk.evid = 3003;
                p.getLevel().setRainTime(ThreadLocalRandom.current().nextInt(168000) + 12000);
            }
            Server.broadcastPacket(Server.getInstance().getOnlinePlayers().values(), pk);
        }
        return false;
    }
}
