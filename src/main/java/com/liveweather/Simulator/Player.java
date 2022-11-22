package com.liveweather.Simulator;

import cn.nukkit.level.Level;
import cn.nukkit.network.SourceInterface;
import cn.nukkit.network.protocol.DataPacket;
import com.liveweather.instances.InstanceManager;

import java.net.InetSocketAddress;
import java.util.Random;

public class Player extends cn.nukkit.Player {
    public Player() {
        super();
    }

    @Override
    public String getName() {
        return "DebugPlayer";
    }

    @Override
    public String getAddress() {
        Random r = new Random();
        return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
    }

    @Override
    public int getPort() {
        return new Random().nextInt(5000);
    }

    @Override
    public boolean hasPermission(String name) {
        return true;
    }

    @Override
    public Level getLevel() {
        new SimulatorLogger().info("Access Level Default");
        return new com.liveweather.Simulator.Level();
    }

    @Override
    public boolean dataPacket(DataPacket packet) {
        new SimulatorLogger().info("Send DataPacket");
        return true;
    }
}
