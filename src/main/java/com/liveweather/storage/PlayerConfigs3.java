package com.liveweather.storage;

import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.threading.Normal;
import java.io.File;
import java.io.IOException;

public class PlayerConfigs3 {
    String location = Server.getInstance().getPluginPath().replace("\\", "") + "LiveWeather/PlayerCity/";
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
