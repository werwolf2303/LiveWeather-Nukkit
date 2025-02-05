package com.liveweather.logging;

import cn.nukkit.Server;

public class LWLogging {
    String location = "";
    public LWLogging() {
        location = Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/sounds/";
    }

    public static void throwable(Throwable throwable) {
        Server.getInstance().getLogger().alert("[LiveWeather::Throwable]", throwable);
    }
    public static void fatal(String message) {
        Server.getInstance().getLogger().emergency(message);
    }
    public static void critical(String message) {
        Server.getInstance().getLogger().error("[LiveWeather::Critical::PANIC] " + message);
        throw new RuntimeException(message);
    }
    public static void extension(String message) {
        Server.getInstance().getLogger().info("[LiveWeather <Extension>] " + message);
    }
    public static void warning(String message) {
        Server.getInstance().getLogger().warning("[LiveWeather::Warning] " + message);
    }
    public static void normal(String message) {
        Server.getInstance().getLogger().info("[LiveWeather] " + message);
    }
    public static void error(String message) {
        Server.getInstance().getLogger().error("[LiveWeather::Error] " + message);
    }
    public static void debugging(String message) {
        Server.getInstance().getLogger().info("[LiveWeather::Debugging] " + message);
    }
}
