package com.liveweather;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import com.liveweather.api.GetWeather;
import com.liveweather.commands.CityChange;
import com.liveweather.commands.CityDelete;
import com.liveweather.commands.CitySetter;
import com.liveweather.commands.WhatsMyWeather;
import com.liveweather.setter.Wetter;
import com.liveweather.setter.WetterService;
import com.liveweather.storage.PlayerConfigs;
import com.liveweather.storage.Zippie;
import com.liveweather.test.TestCommand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.WeakHashMap;

public class Initiator extends PluginBase {
    int t = 0;
    @Override
    public void onLoad() {
        if(!new PlayerConfigs().pluginfolder.exists()) {
            new PlayerConfigs().createPluginFolder();
            new PlayerConfigs().createConfig();
        }
        Server.getInstance().getLogger().info("LiveWeather init");
        if(!new File(Server.getInstance().getFilePath() + "plugins/FormAPI.jar").exists()) {
            Zippie.extractZIP(Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather-Nukkit.jar", Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile");
            File source = new File(Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/FormAPI.jar");
            File dest = new File(Server.getInstance().getFilePath() + "/plugins/FormAPI.jar");
            try {
                Files.copy(source.toPath(), dest.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Server.getInstance().reload();
        }
        Server.getInstance().getCommandMap().register("help", new TestCommand("testweather", "Test the liveweather plugin"));
        Server.getInstance().getCommandMap().register("help", new CityDelete("deletecity", "Deletes your city in liveweather"));
        Server.getInstance().getCommandMap().register("help", new CityChange("changecity", "Change yor liveweather City"));
        Server.getInstance().getCommandMap().register("help", new WhatsMyWeather("whatsmyweather", "Get the current weather for the city you entered"));
        Server.getInstance().getCommandMap().register("help", new CitySetter("setcity", "Set city for LiveWeather"));
        getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
            @Override
            public void run() {
                if(t == 120) {
                    for (Player s : Server.getInstance().getOnlinePlayers().values()) {
                        Server.getInstance().getLogger().debug(s.getName());
                        if(new PlayerConfigs().hasPlayerEnteredCityName(s.getName())) {
                            new Wetter().setWeather(new GetWeather().getWeather(new PlayerConfigs().getPlayerCity(s.getName())), s);
                        }
                    }
                    t=0;
                }else{
                    t=t+1;
                }
            }
        },1);
    }

    @Override
    public void onEnable() {
        Server.getInstance().getPluginManager().registerEvents(new WetterService(), this);
    }

    public Server server() {
        return getServer();
    }
}
