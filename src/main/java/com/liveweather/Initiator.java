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
import com.liveweather.check.Local;
import com.liveweather.check.Performance;
import com.liveweather.commandline.LWLogging;
import com.liveweather.commands.CityChange;
import com.liveweather.commands.CityDelete;
import com.liveweather.commands.CitySetter;
import com.liveweather.commands.WhatsMyWeather;
import com.liveweather.debug.Debug;
import com.liveweather.events.SendMessage;
import com.liveweather.language.Language;
import com.liveweather.location.Tracker;
import com.liveweather.setter.Wetter;
import com.liveweather.setter.WetterService;
import com.liveweather.storage.*;
import com.liveweather.test.TestCommand;
import com.liveweather.time.DateDetect;
import com.liveweather.updater.Update;
import sun.java2d.loops.GeneralRenderer;
import com.liveweather.*;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.liveweather.commandline.LWLogging.unregisterPlugin;

public class Initiator extends PluginBase {
    public static Plugin plugin;
    int t = 0;
    boolean first = true;
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
                new Options().writeConfig("autofindplayercity", "false");
                new Options().writeConfig("language", "en");
            }
            new GetWeather().getWeather("Weinheim");
            //Server.getInstance().getCommandMap().register("help", new TestCommand("testweather", "Test the liveweather plugin"));
            if(new Options().getConfig("autofindplayercity").toLowerCase().equals("false")) {
                Server.getInstance().getCommandMap().register("help", new CityDelete("deletecity", new Language().get("liveweather.commands.citydelete.description")));
                Server.getInstance().getCommandMap().register("help", new CityChange("changecity", new Language().get("liveweather.commands.citychange.description")));
                Server.getInstance().getCommandMap().register("help", new CitySetter("setcity", new Language().get("liveweather.commands.citysetter.description")));
            }
            Server.getInstance().getCommandMap().register("help", new WhatsMyWeather("whatsmyweather", new Language().get("liveweather.commands.whatsmyweather.description")));
            getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
                @Override
                public void run() {
                    if (t == 120) {
                        for (Player s : Server.getInstance().getOnlinePlayers().values()) {
                            if(new Options().getConfig("autofindplayercity").toLowerCase().equals("true")) {
                                if(!new Local().isLocal(s)) {
                                    new Wetter().setWeather(new GetWeather().getWeather(new Tracker().getCity(s.getAddress())), s);
                                }
                            }else {
                                if (new PlayerConfigs2().hasEntered(s.getName())) {
                                    new Wetter().setWeather(new GetWeather().getWeather(new PlayerConfigs2().getCity(s.getName())), s);
                                }
                            }
                        }
                        t = 0;
                    } else {
                        t = t + 1;
                    }
                }
            }, 1);
            getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
                @Override
                public void run() {
                    if (t == 120) {
                        if(new UpdateConfig().read().equals("")) {
                            new UpdateConfig().write(new DateDetect().date());
                        }
                        if(first) {
                            if (new Update().isNewerAvailable()) {
                                new LWLogging().normal("Newer version is available");
                            }
                            first = false;
                        }else {
                            if (!new UpdateConfig().read().equals(new DateDetect().date())) {
                                if (new Update().isNewerAvailable()) {
                                    new LWLogging().normal("Newer version is available");
                                }
                                new UpdateConfig().write(new DateDetect().date());
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
                new LWLogging().critical(new Language().get("liveweather.init.notenoughpower"));
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
                    new LWLogging().normal(new Language().get("liveweather.init.gamerule.every"));
                    level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                }
                Server.getInstance().getPluginManager().registerEvents(new WetterService(), this);
                Server.getInstance().getPluginManager().registerEvents(new SendMessage(), this);
            }
        }else{
            for(Level level : Server.getInstance().getLevels().values()) {
                new LWLogging().normal(new Language().get("liveweather.init.gamerule.setforevery") + level.getName());
                level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }
            Server.getInstance().getPluginManager().registerEvents(new WetterService(), this);
            Server.getInstance().getPluginManager().registerEvents(new SendMessage(), this);
        }
    }

    public Server server() {
        return getServer();
    }
    public static PluginManager getPluginManager() {
        return Server.getInstance().getPluginManager();
    }
}
