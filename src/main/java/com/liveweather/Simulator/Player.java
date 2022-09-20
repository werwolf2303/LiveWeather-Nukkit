package com.liveweather.Simulator;

import cn.nukkit.network.SourceInterface;

import java.net.InetSocketAddress;

public class Player extends cn.nukkit.Player {
    public Player(SourceInterface interfaz, Long clientID, InetSocketAddress socketAddress) {
        super(interfaz, clientID, socketAddress);
    }
}
