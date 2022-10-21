package com.liveweather.test;

import cn.nukkit.Server;
import com.liveweather.GlobalValues;
import com.liveweather.Simulator.Player;
import com.liveweather.api.GetFog;
import com.liveweather.api.GetWeather;
import com.liveweather.bukkit.ChatColor;
import com.liveweather.commandline.LWLogging;
import com.liveweather.extensions.Extension;
import com.liveweather.extensions.ExtensionLoader;
import com.liveweather.instances.InstanceManager;
import com.liveweather.panel.AdminPanel;
import com.liveweather.server.CreateServer;
import com.liveweather.storage.YAMLConfig;
import com.liveweather.translate.Languages;
import com.liveweather.translate.Translate;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        //ExtensionLoader e = new ExtensionLoader();
        //e.load(new File("C:\\Users\\C5350929\\Pictures\\Nukkit\\plugins\\LiveWeather\\extensions\\LWExtension.jar"), false);
        GlobalValues.debug = true;
        new AdminPanel().openLoginForm(new Player());
    }
}