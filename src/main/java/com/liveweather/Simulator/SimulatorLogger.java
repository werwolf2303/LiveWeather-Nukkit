package com.liveweather.Simulator;

import com.liveweather.instances.InstanceManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimulatorLogger {
    public void info(String message) {
        InstanceManager.getDebugLogger().custom("[Server::info] " + message);
    }
    public void player(String message) {
        InstanceManager.getDebugLogger().custom("[Player::Message] " + message);
    }
}
