package com.liveweather.simulator;

import com.liveweather.instances.InstanceManager;

public class SimulatorLogger {
    public void info(String message) {
        InstanceManager.getDebugLogger().custom("[Server::info] " + message);
    }
    public void player(String message) {
        InstanceManager.getDebugLogger().custom("[Player::Message] " + message);
    }
}
