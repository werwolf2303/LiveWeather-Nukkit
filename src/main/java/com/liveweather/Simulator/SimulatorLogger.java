package com.liveweather.Simulator;

import com.liveweather.instances.InstanceManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimulatorLogger {
    public void info(String message) {
        System.out.println("[Server::info] " + message);
        InstanceManager.getConsole().init();
    }
    public void player(String message) {
        System.out.println("[Player::Message] " + message);
        InstanceManager.getConsole().init();
    }
}
