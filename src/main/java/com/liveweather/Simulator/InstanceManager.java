package com.liveweather.Simulator;

import cn.nukkit.Player;
import cn.nukkit.Server;

import java.net.InetSocketAddress;

public class InstanceManager {
    Player p = null;
    Server s = null;
    Client c = null;
    public void simulateClient() {
        c = new Client();
    }
    public void simulatePlayer() {
        p = new Player(null, null, new InetSocketAddress(4599));
    }
    public void simulateServer() {

    }
}
