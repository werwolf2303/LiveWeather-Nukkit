package com.liveweather;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import com.liveweather.api.GetFog;
import com.liveweather.api.GetWeather;
import com.liveweather.check.Local;
import com.liveweather.check.Performance;
import com.liveweather.check.SuccessFullStartup;
import com.liveweather.commandline.LWLogging;
import com.liveweather.commands.*;
import com.liveweather.events.SendMessage;
import com.liveweather.events.ServerEvents;
import com.liveweather.experimental.Cloudly;
import com.liveweather.extensions.ExtensionLoader;
import com.liveweather.formapi.events.EventListener;
import com.liveweather.instances.InstanceManager;
import com.liveweather.language.Language;
import com.liveweather.location.Tracker;
import com.liveweather.server.CreateServer;
import com.liveweather.setter.Wetter;
import com.liveweather.setter.WetterService;
import com.liveweather.storage.*;
import com.liveweather.test.TestCommand;
import com.liveweather.debug.TriggerCommand;
import com.liveweather.utils.PluginAPI;

import java.io.File;
import java.util.ArrayList;

public class Initiator extends PluginBase {
    public static Plugin plugin;
    int t = 0;
    boolean senabled = false;
    public static String pluginlocation = InstanceManager.getServer().getPluginPath() + "/LiveWeather";
    CreateServer server;
    boolean im = false;
    boolean im2 = false;
    @Override
    public void onDisable() {
        //Execute disable on all extensions if there are
        if(!(new File(pluginlocation + "/extensions").listFiles().length == 0)) {
            File extensions = new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/extensions");
            for (File f : extensions.listFiles()) {
                new ExtensionLoader().load(f, true);
            }
        }
        //---
        //Stop configserver if enabled
        if(senabled) {
            server.stop();
            server = null;
        }
        //---
    }
    @Override
    public void onEnable() {
        plugin = this;
        //Check for config file that says not enough power and check if its ignored
        if(new File(new Configuring().config).exists()) {
            if (new Configuring().getConfig("RunUnsupported").equals("false")) {
                new PluginAPI().disableLiveWeather();
            }
        }
        //---
        //Disable third party loggers
        java.util.logging.Logger.getLogger("org.apache.http.conn.util.PublicSuffixMatcherLoader").setLevel(java.util.logging.Level.OFF);
        //---
        //Check if first run when yes create plugin folder
        if (!new File(pluginlocation).exists()) {
            new LWLogging().normal("Init.. !FIRST RUN! This can take a while");
            if(!new File(pluginlocation).mkdir()) {
                new LWLogging().critical("Cant create LiveWeather directory");
            }
        } else {
            new LWLogging().normal("Init..");
        }
        //--
        //Set weather change on all levels to false
        if(!Boolean.parseBoolean(new LWSave().read("Reload"))) {
            for (Level level : InstanceManager.getServer().getLevels().values()) {
                new LWLogging().normal(new Language().get("liveweather.init.gamerule.setforevery").replace("%LEVEL%", level.getName()));
                level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }
        }
        //---
        //Create config file
        if (!new File(LWConfig.config).exists()) {
            new LWConfig().write("apikey", "YOUR_API_KEY");
            new LWConfig().write("autofindplayercity", "false");
            new LWConfig().write("language", "en");
            new LWConfig().write("permissions", "false");
        }
        //---
        //Check if server has enough power or if its ignored when not disable plugin
        if (!new Performance().enoughPower()) {
            if (!new File(new Configuring().config).exists()) {
                new Configuring().createConfig();
                new Configuring().writeConfig("RunUnsupported", "false");
                new LWLogging().critical(new Language().get("liveweather.init.notenoughpower"));
            }
        }
        //---
        //Create extensions folder if it doesnt exist
        if (!new File(InstanceManager.getServer().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "extensions").exists()) {
            new File(InstanceManager.getServer().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "extensions").mkdir();
        }
        //---
        //Check for plugin misbehaviour
        new SuccessFullStartup();
        //---
        //Check if auto location is enabled
        if (new LWConfig().read("autofindplayercity").toLowerCase().equals("false")) {
            InstanceManager.getServer().getCommandMap().register("help", new CityDelete("deletecity", new Language().get("liveweather.commands.citydelete.description")));
            InstanceManager.getServer().getCommandMap().register("help", new CityChange("changecity", new Language().get("liveweather.commands.citychange.description")));
            InstanceManager.getServer().getCommandMap().register("help", new CitySetter("setcity", new Language().get("liveweather.commands.citysetter.description")));
        }
        //---
        //Enable Configserver if set to true
        if (new LWConfig().read("configserver").equals("true")) {
            if (!new LWConfig().read("configserverpassword").equals("")) {
                new LWLogging().warning("Activated very experimental config server");
                senabled = true;
                server = new CreateServer();
                server.start();
            } else {
                new LWLogging().error(new Language().get("livweather.configserver.nopassword") + " Stop");
            }
        }
        //---
        //Enable Cloudly if set to true
        if (new LWConfig().read("cloudly").equals("true")) {
            new LWLogging().normal(new Language().get("liveweather.cloudly.activate"));
            InstanceManager.getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
                @Override
                public void run() {
                    if(!im) {
                        new LWLogging().normal("Start task cloudly");
                    }
                    if (t == 2780) {
                        for (Player s : InstanceManager.getServer().getOnlinePlayers().values()) {
                            if (s.hasPermission("liveweather.commands")) {
                                if (new LWConfig().read("autofindplayercity").toLowerCase().equals("true")) {
                                    if (!new Local().isLocal(s)) {
                                        new Cloudly().setFog(s, new GetFog().getFog(new Tracker().getCity(s.getAddress())));
                                    }
                                } else {
                                    if (new PlayerConfigs3().hasEntered(s.getName())) {
                                        new Cloudly().setFog(s, new GetFog().getFog(new PlayerConfigs3().getCity(s.getName())));
                                    }
                                }
                            }
                        }
                        t = 0;
                    } else {
                        t = t + 1;
                    }
                }
            }, 1);
        }
        //---
        //Enable server debug commands if serverdebug is true
        if(GlobalValues.serverdebug) {
            InstanceManager.getServer().getCommandMap().register("help", new TestCommand("testweather"));
            InstanceManager.getServer().getCommandMap().register("help", new TriggerCommand("trigger"));
        }
        //---
        InstanceManager.getServer().getCommandMap().register("help", new WhatsMyWeather("whatsmyweather", new Language().get("liveweather.commands.whatsmyweather.description")));
        InstanceManager.getServer().getCommandMap().register("help", new PanelCommand("lwpanel", "Opens LiveWeather Admin Panel"));
        //Register weather change event
        InstanceManager.getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
            @Override
            public void run() {
                if(!im2) {
                    new LWLogging().normal("Start task weather service");
                    im2 = true;
                }
                if (t == 2780) {
                    for (Player s : InstanceManager.getServer().getOnlinePlayers().values()) {
                        if (s.hasPermission("liveweather.commands")) {
                            if (new LWConfig().read("autofindplayercity").toLowerCase().equals("true")) {
                                if (!new Local().isLocal(s)) {
                                    new Wetter().setWeather(new GetWeather().getWeather(new Tracker().getCity(s.getAddress())), s);
                                }
                            } else {
                                if (new PlayerConfigs3().hasEntered(s.getName())) {
                                    new Wetter().setWeather(new GetWeather().getWeather(new PlayerConfigs3().getCity(s.getName())), s);
                                }
                            }
                        }
                    }
                    t = 0;
                } else {
                    t = t + 1;
                }
            }
        }, 1);
        //---
        //Load extensions if there are
        if(!(new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/extensions").listFiles().length == 0)) {
            File extensions = new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/extensions");
            for (File f : extensions.listFiles()) {
                new ExtensionLoader().load(f, false);
            }
        }
        //---
        //Registering event listeners
        InstanceManager.getServer().getPluginManager().registerEvents(new WetterService(), this);
        InstanceManager.getServer().getPluginManager().registerEvents(new SendMessage(), this);
        InstanceManager.getServer().getPluginManager().registerEvents(new EventListener(), this);
        InstanceManager.getServer().getPluginManager().registerEvents(new ServerEvents(), this);
        //---
        new LWLogging().normal(new Language().get("liveweather.init.finished"));
    }
    public Server server() {
        return getServer();
    }
}
