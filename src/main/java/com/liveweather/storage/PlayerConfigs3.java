package com.liveweather.storage;

import com.liveweather.commandline.LWLogging;
import com.liveweather.instances.InstanceManager;
import com.liveweather.language.Language;
import com.liveweather.threading.Normal;
import java.io.File;

@SuppressWarnings({"SameReturnValue", "unused"})
public class PlayerConfigs3 {
    final String location = InstanceManager.getServer().getPluginPath().replace("\\", "") + "LiveWeather/PlayerCity/";
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public PlayerConfigs3() {
        if (!new File(location).exists()) {
            new File(location).mkdir();
        }
    }
    public void createPlayer(String playername, String city) {
        new Normal(createplayer(playername, city)).start();
    }
    public void deletePlayer(String playername) {
        new Normal(deleteplayer(playername)).start();
    }
    public void changePlayer(String playername, String city) {
        new Normal(changeplayer(playername, city)).start();
    }
    public String getCity(String playername) {
        try {
            return new PlayerConfig().getCity(playername);
        }catch (NullPointerException exc) {
            new LWLogging().critical(new Language().get("liveweather.playerconfig.weather.cantget"));
        }
        return "InvalidCity";
    }
    public boolean hasEntered(String playername) {
        return new PlayerConfig().hasEntered(playername);
    }
    Runnable deleteplayer(String playername) {
        new PlayerConfig().deleteplayer("City");
        return null;
    }
    Runnable changeplayer(String playername, String city) {
        new PlayerConfig().changeplayer(playername,city);
        return null;
    }
    Runnable createplayer(String playername, String cityname) {
        new PlayerConfig().createplayer(playername,cityname);
        return null;
    }
}
