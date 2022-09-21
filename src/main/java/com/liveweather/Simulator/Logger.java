package com.liveweather.Simulator;

import cn.nukkit.utils.MainLogger;

public class Logger extends MainLogger {

    @Override
    public void emergency(String message) {
        System.out.println(message);
    }

    @Override
    public void alert(String message) {
        System.out.println(message);
    }

    @Override
    public void critical(String message) {
        System.out.println(message);
    }

    @Override
    public void error(String message) {
        System.out.println(message);
    }

    @Override
    public void warning(String message) {
        System.out.println(message);
    }

    @Override
    public void notice(String message) {
        System.out.println(message);
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void debug(String message) {
        System.out.println(message);
    }

}
