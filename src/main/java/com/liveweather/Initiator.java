package com.liveweather;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import com.liveweather.Simulator.Logger;
import com.liveweather.api.GetFog;
import com.liveweather.api.GetWeather;
import com.liveweather.check.Local;
import com.liveweather.check.Performance;
import com.liveweather.check.SuccessFullStartup;
import com.liveweather.commandline.LWLogging;
import com.liveweather.commands.CityChange;
import com.liveweather.commands.CityDelete;
import com.liveweather.commands.CitySetter;
import com.liveweather.commands.WhatsMyWeather;
import com.liveweather.events.OnStartup;
import com.liveweather.events.SendMessage;
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
import com.liveweather.utils.PluginAPI;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.liveweather.commandline.LWLogging.unregisterPlugin;

public class Initiator extends PluginBase {
    public static boolean doUpdate = false;
    public static Plugin plugin;
    int t = 0;
    boolean first = true;
    boolean senabled = false;
    String pluginlocation = InstanceManager.getServer().getPluginPath() + "/LiveWeather";
    CreateServer server;
    @Override
    public void onLoad() {
        if (!new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather").exists()) {
            new LWLogging().normal("Init.. !FIRST RUN! This can take a while");
        } else {
            new LWLogging().normal("Init..");
        }
        if (!new File(pluginlocation).exists()) {
            new File(pluginlocation).mkdir();
        }
        if (!new File(new YAMLConfig().getLocation()).exists()) {
            new YAMLConfig().create();
            new YAMLConfig().write("apikey", "YOUR_API_KEY");
            new YAMLConfig().write("autofindplayercity", "false");
            new YAMLConfig().write("language", "en");
            new YAMLConfig().write("permissions", "false");
        }
        java.util.logging.Logger.getLogger("org.apache.http.conn.util.PublicSuffixMatcherLoader").setLevel(java.util.logging.Level.OFF);
        if(new File(InstanceManager.getServer().getFilePath()+"plugins/FormAPI.jar").exists()) {
            new LWLogging().normal("Found old FormAPI! Delete...");
            new PluginAPI().delete("formapi");
            new LWLogging().normal("Done...");
        }
        /*if (!new File(InstanceManager.getServer().getFilePath() + "plugins/FormAPI.jar").exists()) {
            Zippie.extractZIP(InstanceManager.getServer().getFilePath() + "/plugins/" + "LiveWeather-Nukkit.jar", InstanceManager.getServer().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile");
            File source = new File(InstanceManager.getServer().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/FormAPI.jar");
            File dest = new File(InstanceManager.getServer().getFilePath() + "/plugins/FormAPI.jar");
            try {
                Files.copy(source.toPath(), dest.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InstanceManager.getServer().getPluginManager().loadPlugin(new File(InstanceManager.getServer().getFilePath() + "/plugins/FormAPI.jar"));
        }*/
        if (new Performance().enoughPower()) {
            if (!new File(InstanceManager.getServer().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "extensions").exists()) {
                new File(InstanceManager.getServer().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "extensions").mkdir();
            }
            new SuccessFullStartup();
            InstanceManager.getServer().getCommandMap().register("help", new TestCommand("testweather"));
            if (new YAMLConfig().read("autofindplayercity").toLowerCase().equals("false")) {
                InstanceManager.getServer().getCommandMap().register("help", new CityDelete("deletecity", new Language().get("liveweather.commands.citydelete.description")));
                InstanceManager.getServer().getCommandMap().register("help", new CityChange("changecity", new Language().get("liveweather.commands.citychange.description")));
                InstanceManager.getServer().getCommandMap().register("help", new CitySetter("setcity", new Language().get("liveweather.commands.citysetter.description")));
            }
            InstanceManager.getServer().getCommandMap().register("help", new WhatsMyWeather("whatsmyweather", new Language().get("liveweather.commands.whatsmyweather.description")));
            if (new YAMLConfig().read("configserver").equals("true")) {
                if (!new YAMLConfig().read("configserverpassword").equals("")) {
                    new LWLogging().warning("Activated very experimental config server");
                    senabled = true;
                    server = new CreateServer();
                    server.start();
                } else {
                    new LWLogging().error(new Language().get("livweather.configserver.nopassword") + " Stop");
                }
            }
            if (new YAMLConfig().read("cloudly").equals("true")) {
                new LWLogging().normal(new Language().get("liveweather.cloudly.activate"));
                InstanceManager.getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
                    @Override
                    public void run() {
                        if (t == 2780) {
                            for (Player s : InstanceManager.getServer().getOnlinePlayers().values()) {
                                if (s.hasPermission("liveweather.commands")) {
                                    if (new YAMLConfig().read("autofindplayercity").toLowerCase().equals("true")) {
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
            InstanceManager.getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
                @Override
                public void run() {
                    if (t == 2780) {
                        for (Player s : InstanceManager.getServer().getOnlinePlayers().values()) {
                            if (s.hasPermission("liveweather.commands")) {
                                if (new YAMLConfig().read("autofindplayercity").toLowerCase().equals("true")) {
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
            new OnStartup();
            File extensions = new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/extensions");
            for (File f : extensions.listFiles()) {
                new ExtensionLoader().load(f, false);
            }

        } else {
            if (!new File(new Configuring().config).exists()) {
                new Configuring().createConfig();
                new Configuring().writeConfig("RunUnsupported", "false");
                new LWLogging().critical(new Language().get("liveweather.init.notenoughpower"));
            }
        }
        new LWLogging().normal(new Language().get("liveweather.init.finished"));
    }

    @Override
    public void onDisable() {
        File extensions = new File(InstanceManager.getServer().getPluginPath() + "/LiveWeather/extensions");
        for(File f : extensions.listFiles()) {
            new ExtensionLoader().load(f, true);
        }
        if(senabled) {
            server.stop();
            server = null;
        }

    }

    @Override
    public void onEnable() {
        plugin = this;
        if(new File(new Configuring().config).exists()) {
            if (new Configuring().getConfig("RunUnsupported").equals("false")) {
                Plugin plugin = InstanceManager.getServer().getPluginManager().getPlugin("LiveWeather");
                unregisterPlugin(plugin);
                InstanceManager.getServer().getPluginManager().disablePlugin(plugin);
            } else {
                for(Level level : InstanceManager.getServer().getLevels().values()) {
                    new LWLogging().normal(new Language().get("liveweather.init.gamerule.every"));
                    level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                }
                InstanceManager.getServer().getPluginManager().registerEvents(new WetterService(), this);
                InstanceManager.getServer().getPluginManager().registerEvents(new SendMessage(), this);
            }
        }else{
            for(Level level : InstanceManager.getServer().getLevels().values()) {
                new LWLogging().normal(new Language().get("liveweather.init.gamerule.setforevery") + level.getName());
                level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }
            InstanceManager.getServer().getPluginManager().registerEvents(new WetterService(), this);
            InstanceManager.getServer().getPluginManager().registerEvents(new SendMessage(), this);
        }
        InstanceManager.getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
    public Server server() {
        return getServer();
    }
    public static PluginManager getPluginManager() {
        return InstanceManager.getServer().getPluginManager();
    }
}
