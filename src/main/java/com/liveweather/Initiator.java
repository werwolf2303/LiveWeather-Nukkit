package com.liveweather;

import cn.nukkit.Server;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import com.liveweather.geocoding.DummyGeocodingProvider;
import com.liveweather.geocoding.GeoapifyGeocodingProvider;
import com.liveweather.geocoding.GeocodingProvider;
import com.liveweather.storage.JSONStorage;
import com.liveweather.storage.Storage;
import com.liveweather.tracking.DummyTrackingProvider;
import com.liveweather.tracking.IPAPiCommercialProvider;
import com.liveweather.tracking.IPAPiProvider;
import com.liveweather.tracking.TrackingProvider;
import com.liveweather.weatherproviders.DummyWeatherProvider;
import com.liveweather.commands.CityChange;
import com.liveweather.commands.CityDelete;
import com.liveweather.commands.CityGetter;
import com.liveweather.commands.CitySetter;
import com.liveweather.config.ConfigValues;
import com.liveweather.config.Config;
import com.liveweather.events.PlayerEvents;
import com.liveweather.language.libLanguage;
import com.liveweather.logging.LWLogging;
import com.liveweather.storage.DummyStorage;
import com.liveweather.weatherproviders.OpenMeteoCProvider;
import com.liveweather.weatherproviders.OpenMeteoProvider;
import com.liveweather.weatherproviders.WeatherDataProvider;

import java.io.File;
import java.nio.file.FileSystemException;

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
                    new OpenMeteoCProvider()
            };
            PublicValues.trackingProviders = new TrackingProvider[] {
                    new IPAPiProvider(),
                    new IPAPiCommercialProvider(),
                    new DummyTrackingProvider()
            };
            PublicValues.playerStorageProviders = new Storage[] {
                    new DummyStorage(),
                    new JSONStorage()
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
                Server.getInstance().getCommandMap().register("help", new CityDelete("lwdeletecity", PublicValues.language.translate("liveweather.commands.citydelete.description")));
                Server.getInstance().getCommandMap().register("help", new CityChange("lwchangecity", PublicValues.language.translate("liveweather.commands.citychange.description")));
                Server.getInstance().getCommandMap().register("help", new CitySetter("lwsetcity", PublicValues.language.translate("liveweather.commands.citysetter.description")));
                Server.getInstance().getCommandMap().register("help", new CityGetter("lwgetcity", PublicValues.language.translate("liveweather.commands.citygetter.description")));
            }
            Server.getInstance().getPluginManager().registerEvents(new PlayerEvents(), this);
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
            LWLogging.error("Failed saving player storage");
            LWLogging.throwable(e);
        }
    }
}
