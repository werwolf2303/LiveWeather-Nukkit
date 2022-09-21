package com.liveweather.api;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.level.ThunderChangeEvent;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.level.Sound;
import cn.nukkit.network.protocol.LevelEventPacket;
import com.liveweather.instances.InstanceManager;

import java.util.concurrent.ThreadLocalRandom;

public class SetWeather {
    boolean raining = false;
    boolean thundering = false;
    int raintime = 0;
    int thundertime = 0;
    int seconds = 12000;
    public void setRainTime(int rainTime) {
        raintime = rainTime;
    }
    public void setThunderTime(int thunderTime) {
        thundertime = thunderTime;
    }
    public void setRaining(Player p) {
        WeatherChangeEvent ev = new WeatherChangeEvent(p.getLevel(), raining);
        InstanceManager.getServer().getPluginManager().callEvent(ev);
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = 3001;
        int time = ThreadLocalRandom.current().nextInt(12000) + 12000;
        pk.data = time;
        this.setRainTime(time);
        Player[] p2 = new Player[] {p};
        Server.broadcastPacket(p2, pk);
    }
    public void setClear(Player p) {
        noRaining(p);
        noThundering(p);
        setRainTime(seconds * 20);
        setThunderTime(seconds * 20);
    }
    public void noThundering(Player p) {
        WeatherChangeEvent ev = new WeatherChangeEvent(p.getLevel(), raining);
        InstanceManager.getServer().getPluginManager().callEvent(ev);
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = 3004;
        this.setRainTime(ThreadLocalRandom.current().nextInt(168000) + 12000);
        Player[] p2 = new Player[] {p};
        Server.broadcastPacket(p2, pk);
    }
    public void noRaining(Player p) {
        WeatherChangeEvent ev = new WeatherChangeEvent(p.getLevel(), raining);
        InstanceManager.getServer().getPluginManager().callEvent(ev);
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = 3003;
        this.setRainTime(ThreadLocalRandom.current().nextInt(168000) + 12000);
        Player[] p2 = new Player[] {p};
        InstanceManager.getServer().broadcastPacket(p2, pk);
    }
    public void setThundering(Player p) {
        ThunderChangeEvent ev = new ThunderChangeEvent(p.getLevel(), thundering);
        InstanceManager.getServer().getPluginManager().callEvent(ev);
        this.thundering = thundering;
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = 3002;
        int time = ThreadLocalRandom.current().nextInt(12000) + 3600;
        pk.data = time;
        this.setThunderTime(time);
        Player[] p2 = new Player[] {p};
        InstanceManager.getServer().broadcastPacket(p2, pk);
    }
}
