package com.liveweather;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLoader;
import cn.nukkit.plugin.PluginManager;
import com.liveweather.api.GetWeather;
import com.liveweather.check.Performance;
import com.liveweather.commandline.LWLogging;
import com.liveweather.commands.CityChange;
import com.liveweather.commands.CityDelete;
import com.liveweather.commands.CitySetter;
import com.liveweather.commands.WhatsMyWeather;
import com.liveweather.setter.Wetter;
import com.liveweather.setter.WetterService;
import com.liveweather.storage.Configuring;
import com.liveweather.storage.Options;
import com.liveweather.storage.PlayerConfigs2;
import com.liveweather.storage.Zippie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.liveweather.commandline.LWLogging.unregisterPlugin;

public class Initiator extends PluginBase {
    public static Plugin plugin;
    int t = 0;
    @Override
    public void onLoad() {
            if (!new PlayerConfigs2().pluginfolder.exists()) {
                new PlayerConfigs2().createPluginFolder();
            }
            new LWLogging().normal("Init");
            if (!new File(Server.getInstance().getFilePath() + "plugins/FormAPI.jar").exists()) {
                Zippie.extractZIP(Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather-Nukkit.jar", Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile");
                File source = new File(Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/FormAPI.jar");
                File dest = new File(Server.getInstance().getFilePath() + "/plugins/FormAPI.jar");
                try {
                    Files.copy(source.toPath(), dest.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Server.getInstance().getPluginManager().loadPlugin(new File(Server.getInstance().getFilePath() + "/plugins/FormAPI.jar"));
            }
        if(new Performance().enoughPower()) {
            if(!new File(new Options().config).exists()) {
                new Options().createConfig();
                new Options().writeConfig("apikey", "YOUR_API_KEY");
            }
            new GetWeather().getWeather("Weinheim");
            //Server.getInstance().getCommandMap().register("help", new TestCommand("testweather", "Test the liveweather plugin"));
            Server.getInstance().getCommandMap().register("help", new CityDelete("deletecity", "Deletes your city in liveweather"));
            Server.getInstance().getCommandMap().register("help", new CityChange("changecity", "Change yor liveweather City"));
            Server.getInstance().getCommandMap().register("help", new WhatsMyWeather("whatsmyweather", "Get the current weather for the city you entered"));
            Server.getInstance().getCommandMap().register("help", new CitySetter("setcity", "Set city for LiveWeather"));
            getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
                @Override
                public void run() {
                    if (t == 120) {
                        for (Player s : Server.getInstance().getOnlinePlayers().values()) {
                            Server.getInstance().getLogger().debug(s.getName());
                            if (new PlayerConfigs2().hasEntered(s.getName())) {
                                new Wetter().setWeather(new GetWeather().getWeather(new PlayerConfigs2().getCity(s.getName())), s);
                            }
                        }
                        t = 0;
                    } else {
                        t = t + 1;
                    }
                }
            }, 1);
        }else{
            if(!new File(new Configuring().config).exists()) {
                new Configuring().createConfig();
                new Configuring().writeConfig("RunUnsupported", "false");
                new LWLogging().critical("Not enough Power to run this! If you still want to run this edit the config.cfg.. and change 'RunUnsupported' to 'true'");
            }
        }
    }

    @Override
    public void onEnable() {
        plugin = this;
        if(new File(new Configuring().config).exists()) {
            if (new Configuring().getConfig("RunUnsupported").equals("false")) {
                Plugin plugin = Server.getInstance().getPluginManager().getPlugin("LiveWeather");
                unregisterPlugin(plugin);
                Server.getInstance().getPluginManager().disablePlugin(plugin);
            } else {
                for(Level level : Server.getInstance().getLevels().values()) {
                    new LWLogging().normal("Set gamerule DO_WEATHER_CYCLE to false in every World");
                    level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                }
                Server.getInstance().getPluginManager().registerEvents(new WetterService(), this);
            }
        }else{
            for(Level level : Server.getInstance().getLevels().values()) {
                new LWLogging().normal("Set gamerule DO_WEATHER_CYCLE to false in the World: " + level.getName());
                level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }
            Server.getInstance().getPluginManager().registerEvents(new WetterService(), this);
        }
    }

    public Server server() {
        return getServer();
    }
    public static PluginManager getPluginManager() {
        return Server.getInstance().getPluginManager();
    }
}
