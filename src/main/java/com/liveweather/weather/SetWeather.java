package com.liveweather.weather;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.mob.EntityWither;
import cn.nukkit.event.level.ThunderChangeEvent;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.network.protocol.BossEventPacket;
import cn.nukkit.network.protocol.ChunkRadiusUpdatedPacket;
import cn.nukkit.network.protocol.LevelEventPacket;

import java.util.concurrent.ThreadLocalRandom;

public class SetWeather {
    private static boolean raining = false;
    private static boolean thundering = false;
    public static void setRaining(Player p) {
        WeatherChangeEvent ev = new WeatherChangeEvent(p.getLevel(), raining);
        Server.getInstance().getPluginManager().callEvent(ev);
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = 3001;
        int time = ThreadLocalRandom.current().nextInt(12000) + 12000;
        pk.data = time;
        Player[] p2 = new Player[] {p};
        Server.broadcastPacket(p2, pk);
    }
    public static void setClear(Player p) {
        noRaining(p);
        noThundering(p);
    }
    public static void noThundering(Player p) {
        WeatherChangeEvent ev = new WeatherChangeEvent(p.getLevel(), raining);
        Server.getInstance().getPluginManager().callEvent(ev);
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = 3004;
        Player[] p2 = new Player[] {p};
        Server.broadcastPacket(p2, pk);
    }
    public static void noRaining(Player p) {
        WeatherChangeEvent ev = new WeatherChangeEvent(p.getLevel(), raining);
        Server.getInstance().getPluginManager().callEvent(ev);
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = 3003;
        Player[] p2 = new Player[] {p};
        Server.broadcastPacket(p2, pk);
    }
    public static void setThundering(Player p) {
        ThunderChangeEvent ev = new ThunderChangeEvent(p.getLevel(), thundering);
        Server.getInstance().getPluginManager().callEvent(ev);
        SetWeather.thundering = true;
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = 3002;
        int time = ThreadLocalRandom.current().nextInt(12000) + 3600;
        pk.data = time;
        Player[] p2 = new Player[] {p};
        Server.broadcastPacket(p2, pk);
    }
}
