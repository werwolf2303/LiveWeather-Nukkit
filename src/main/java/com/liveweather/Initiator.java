package com.liveweather;

import cn.nukkit.Server;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
import com.liveweather.commands.*;
import com.liveweather.config.Config;
import com.liveweather.config.ConfigValues;
import com.liveweather.events.PlayerEvents;
import com.liveweather.geocoding.DummyGeocodingProvider;
import com.liveweather.geocoding.GeoapifyGeocodingProvider;
import com.liveweather.geocoding.GeocodingProvider;
import com.liveweather.language.libLanguage;
import com.liveweather.logging.LWLogging;
import com.liveweather.storage.DummyStorage;
import com.liveweather.storage.JSONStorage;
import com.liveweather.storage.RAMStorage;
import com.liveweather.storage.Storage;
import com.liveweather.tracking.*;
import com.liveweather.weather.SetWeather;
import com.liveweather.weatherproviders.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.concurrent.TimeUnit;

public class Initiator extends PluginBase {
    public static Plugin lwplugin;

    @Override
    public void onEnable() {
        try {
            lwplugin = this;

            PublicValues.pluginpath = new File(Server.getInstance().getPluginPath(), "LiveWeather");
            if (!PublicValues.pluginpath.exists()) {
                if (!PublicValues.pluginpath.mkdir()) {
                    throw new RuntimeException(new FileSystemException(PublicValues.pluginpath.getAbsolutePath()));
                }
            }
            PublicValues.configPath = new File(PublicValues.pluginpath, "config.yml");
            PublicValues.config = new Config();

            PublicValues.weatherDataProviders = new WeatherDataProvider[] {
                    new DummyWeatherProvider(),
                    new OpenMeteoProvider(),
                    new OpenMeteoCProvider(),
                    new OpenWeatherProvider()
            };
            PublicValues.trackingProviders = new TrackingProvider[] {
                    new IPAPiProvider(),
                    new IPAPiCommercialProvider(),
                    new DummyTrackingProvider(),
                    new TestTrackingProvider()
            };
            PublicValues.playerStorageProviders = new Storage[] {
                    new DummyStorage(),
                    new JSONStorage(),
                    new RAMStorage()
            };
            PublicValues.geocodingProviders = new GeocodingProvider[] {
                    new DummyGeocodingProvider(),
                    new GeoapifyGeocodingProvider()
            };
            PublicValues.language = new libLanguage();
            PublicValues.language.setLanguageFolder("lang");
            PublicValues.language.setNoAutoFindLanguage(PublicValues.config.getString(ConfigValues.language.name));
            for(Storage storage : PublicValues.playerStorageProviders) {
                if(storage.name().equals(PublicValues.config.getString(ConfigValues.storageType.name))) {
                    PublicValues.playerStorageProvider = storage;
                    storage.init();
                    break;
                }
            }
            if(PublicValues.playerStorageProvider == null) {
                LWLogging.fatal("Invalid storage type selected: " + PublicValues.config.getString(ConfigValues.storageType.name));
                getPluginLoader().disablePlugin(this);
                return;
            }
            for(WeatherDataProvider provider : PublicValues.weatherDataProviders) {
                if(provider.name().equals(PublicValues.config.getString(ConfigValues.apiProvider.name))) {
                    PublicValues.weatherDataProvider = provider;
                    break;
                }
            }
            if(PublicValues.weatherDataProvider == null) {
                LWLogging.fatal("Invalid weather provider selected: " + PublicValues.config.getString(ConfigValues.apiProvider.name));
                getPluginLoader().disablePlugin(this);
                return;
            }
            if(PublicValues.config.getBoolean(ConfigValues.autoFind.name)) {
                for(TrackingProvider provider : PublicValues.trackingProviders) {
                    if(provider.name().equals(PublicValues.config.getString(ConfigValues.trackingProvider.name))) {
                        PublicValues.trackingProvider = provider;
                        PublicValues.trackingProvider.init();
                        break;
                    }
                }
                if(PublicValues.trackingProvider == null) {
                    LWLogging.fatal("Invalid tracking provider selected: " + PublicValues.config.getString(ConfigValues.trackingProvider.name));
                    getPluginLoader().disablePlugin(this);
                    return;
                }
            }
            for(GeocodingProvider provider : PublicValues.geocodingProviders) {
                if(provider.name().equals(PublicValues.config.getString(ConfigValues.geocodingProvider.name))) {
                    PublicValues.geocodingProvider = provider;
                    break;
                }
            }
            if(PublicValues.geocodingProvider == null) {
                LWLogging.fatal("Invalid geo provider selected: " + PublicValues.config.getString(ConfigValues.geocodingProvider.name));
                getPluginLoader().disablePlugin(this);
                return;
            }
            for (Level level : Server.getInstance().getLevels().values()) {
                level.getGameRules().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }
            if (!PublicValues.config.getBoolean(ConfigValues.autoFind.name)) {
                Server.getInstance().getCommandMap().register("", new LocationDelete("lwdeletelocation", PublicValues.language.translate("liveweather.commands.locationdelete.description")));
                Server.getInstance().getCommandMap().register("", new LocationChange("lwchangelocation", PublicValues.language.translate("liveweather.commands.locationchange.description")));
            }
            Server.getInstance().getCommandMap().register("", new LocationSet("lwsetlocation", PublicValues.language.translate("liveweather.commands.locationset.description")));
            if (PublicValues.config.getBoolean(ConfigValues.debug.name)) {
                Server.getInstance().getCommandMap().register("", new DebugGetWeather());
                Server.getInstance().getCommandMap().register("", new DebugGetLocation());
            }
            Server.getInstance().getPluginManager().registerEvents(new PlayerEvents(), this);
            Server.getInstance().getScheduler().scheduleRepeatingTask(this, new Task() {
                @Override
                public void onRun(int i) {
                    Server.getInstance().getOnlinePlayers().forEach((uuid, player) -> {
                        if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId()) && PublicValues.playerStorageProvider.didAccept(player.getUniqueId())) {
                            if (PublicValues.config.getBoolean(ConfigValues.debug.name)) {
                               LWLogging.debugging("Getting weather for: " + player.getName());
                            }
                            try {
                                Float[] latLon = PublicValues.playerStorageProvider.getSettingOfPlayer(player.getUniqueId());
                                switch (PublicValues.weatherDataProvider.getWeather(latLon[0], latLon[1], player)) {
                                    case RAIN:
                                        SetWeather.setRaining(player);
                                        break;
                                    case CLEAR:
                                        SetWeather.setClear(player);
                                        break;
                                    case THUNDER:
                                        SetWeather.setThundering(player);
                                }
                            } catch (IOException e) {
                                LWLogging.error("Failed getting weather for: " + player.getName());
                                LWLogging.throwable(e);
                            }
                            try {
                                Thread.sleep(TimeUnit.SECONDS.toMillis(PublicValues.config.getInt(ConfigValues.apiCallsPerMinute.name) / 60));
                            } catch (InterruptedException e) {
                                LWLogging.error("Couldn't throttle api requests");
                                LWLogging.throwable(e);
                            }
                        }
                    });
                }
            }, PublicValues.weatherDataProvider.getRefreshPeriod());
        }catch (Exception e) {
            LWLogging.error("Failed to initialize LiveWeather");
            LWLogging.throwable(e);
            getPluginLoader().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try {
            PublicValues.playerStorageProvider.onUnload();
        } catch (Exception e) {
            LWLogging.error("Failed to unload player storage");
            LWLogging.throwable(e);
        }

        if(PublicValues.trackingProvider != null) {
            try {
                PublicValues.trackingProvider.unload();
            } catch (Exception e) {
                LWLogging.error("Failed to unload tracking provider");
                LWLogging.throwable(e);
            }
        }
    }
}
