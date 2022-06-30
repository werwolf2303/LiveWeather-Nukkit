package com.liveweather.test;

import com.liveweather.bukkit.ChatColor;
import com.liveweather.extensions.Extension;
import com.liveweather.extensions.ExtensionLoader;
import com.liveweather.storage.YAMLConfig;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        ExtensionLoader e = new ExtensionLoader();
        e.load(new File("C:\\Users\\C5350929\\Pictures\\Nukkit\\plugins\\LiveWeather\\extensions\\LWExtension.jar"), false);
    }
}