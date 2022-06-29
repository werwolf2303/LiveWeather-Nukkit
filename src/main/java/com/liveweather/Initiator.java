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
import com.liveweather.commands.CityChange;
import com.liveweather.commands.CityDelete;
import com.liveweather.commands.CitySetter;
import com.liveweather.commands.WhatsMyWeather;
import com.liveweather.events.*;
import com.liveweather.experimental.Cloudly;
import com.liveweather.extensions.ExtensionLoader;
import com.liveweather.language.Language;
import com.liveweather.location.Tracker;
import com.liveweather.server.CreateServer;
import com.liveweather.setter.Wetter;
import com.liveweather.setter.WetterService;
import com.liveweather.storage.Configuring;
import com.liveweather.storage.YAMLConfig;
import com.liveweather.storage.PlayerConfig;

import java.io.File;

import static com.liveweather.commandline.LWLogging.unregisterPlugin;

public class Initiator extends PluginBase {
    public static Plugin plugin;
    int t = 0;
    boolean first = true;
    boolean senabled = false;
    String pluginlocation = Server.getInstance().getPluginPath() + "/LiveWeather";
    File pluginloc = new File(pluginlocation);
    CreateServer server;

    @Override
    public void onLoad() {
        new onRun();
        if (new Performance().enoughPower()) {
            new SuccessFullStartup();

            //Register Commands
            if (new YAMLConfig().read("autofindplayercity").toLowerCase().equals("false")) {
                Server.getInstance().getCommandMap().register("help", new CityDelete("deletecity", new Language().get("liveweather.commands.citydelete.description")));
                Server.getInstance().getCommandMap().register("help", new CityChange("changecity", new Language().get("liveweather.commands.citychange.description")));
                Server.getInstance().getCommandMap().register("help", new CitySetter("setcity", new Language().get("liveweather.commands.citysetter.description")));
            }
            Server.getInstance().getCommandMap().register("help", new WhatsMyWeather("whatsmyweather", new Language().get("liveweather.commands.whatsmyweather.description")));

            //Configserver
            if (new YAMLConfig().read("configserver").equals("true")) {
                if (!new YAMLConfig().read("configserverpassword").equals("")) {
                    new LWLogging().warning("Activated very experimental config server");
                    senabled = true;
                    server = new CreateServer();
                    server.start();
                } else {
                    new LWLogging().error("Experimental ConfigServer set 'configserverpassword' :: Stop");
                }
            }

            //Cloudly
            if (new YAMLConfig().read("cloudly").equals("true")) {
                new LWLogging().normal("Activated experimental view distance fog [Cloudly]");
                getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
                    @Override
                    public void run() {
                        if (t == 240) {
                            for (Player s : Server.getInstance().getOnlinePlayers().values()) {
                                if (s.hasPermission("liveweather.commands")) {
                                    if (new YAMLConfig().read("autofindplayercity").toLowerCase().equals("true")) {
                                        if (!new Local().isLocal(s)) {
                                            new Cloudly().setFog(s, new GetFog().getFog(new Tracker().getCity(s.getAddress())));
                                        }
                                    } else {
                                        if (new PlayerConfig().hasEntered(s.getName())) {
                                            new Cloudly().setFog(s, new GetFog().getFog(new PlayerConfig().getCity(s.getName())));
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

            //Normal weather repeat task
            getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
                @Override
                public void run() {
                    if (t == 120) {
                        for (Player s : Server.getInstance().getOnlinePlayers().values()) {
                            if (s.hasPermission("liveweather.commands")) {
                                if (new YAMLConfig().read("autofindplayercity").toLowerCase().equals("true")) {
                                    if (!new Local().isLocal(s)) {
                                        new Wetter().setWeather(new GetWeather().getWeather(new Tracker().getCity(s.getAddress())), s);
                                    }
                                } else {
                                    if (new PlayerConfig().hasEntered(s.getName())) {
                                        new Wetter().setWeather(new GetWeather().getWeather(new PlayerConfig().getCity(s.getName())), s);
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

            //Check language
            if (new YAMLConfig().read("language").equals("en")) {
            } else {
                if (new YAMLConfig().read("language").equals("chs")) {
                } else {
                    if (new YAMLConfig().read("language").equals("de")) {
                    } else {
                        new LWLogging().warning("Unsupported Language '" + new YAMLConfig().read("language") + "' ! Use libretranslate api");
                    }
                }
            }

            //Init extensions
            File extensions = new File(Server.getInstance().getPluginPath() + "/LiveWeather/extensions");
            for (File f : extensions.listFiles()) {
                new ExtensionLoader().load(f, false);
            }

            new onFinishedLoading();
        } else {

            //When hardware specs are lower then expected
            if (!new File(new Configuring().config).exists()) {
                new Configuring().createConfig();
                new Configuring().writeConfig("RunUnsupported", "false");
                new LWLogging().critical(new Language().get("liveweather.init.notenoughpower"));
            }
        }
    }

    @Override
    public void onDisable() {
        //Fire onDisable events in extensions
        File extensions = new File(Server.getInstance().getPluginPath() + "/LiveWeather/extensions");
        for(File f : extensions.listFiles()) {
            new ExtensionLoader().load(f, true);
        }

        //Stop configserver when its enabled
        if(senabled) {
            server.stop();
            server = null;
        }

        new onDisable();
    }

    @Override
    public void onEnable() {
        plugin = this;

        //Check if RunUnsupported is true
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

            //Register events and set do_weather_cycle in every world to false
            for(Level level : Server.getInstance().getLevels().values()) {
                new LWLogging().normal(new Language().get("liveweather.init.gamerule.setforevery") + level.getName());
                level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }
            Server.getInstance().getPluginManager().registerEvents(new WetterService(), this);
            Server.getInstance().getPluginManager().registerEvents(new SendMessage(), this);
            new onFinishedEnabling();
        }
    }
}
