package com.liveweather.storage;

import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.threading.Normal;
import java.io.File;

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
            return new PlayerYAMLConfig(playername).read("City");
        }catch (NullPointerException exc) {
            new LWLogging().critical(new Language().get("liveweather.playerconfig.weather.cantget"));
        }
        return "NULL";
    }
    public boolean hasEntered(String playername) {
        return new PlayerYAMLConfig(playername).exist();
    }
    Runnable deleteplayer(String playername) {
        new PlayerYAMLConfig(playername).delete("City");
        if(!new PlayerYAMLConfig(playername).read("City").equals("")) {
            new LWLogging().critical(new Language().get("liveweather.playerconfig.weather.cantdelete"));
        }
        return null;
    }
    Runnable changeplayer(String playername, String city) {
        new PlayerYAMLConfig(playername).delete("City");
        new PlayerYAMLConfig(playername).write("City", city);
        return null;
    }
    Runnable createplayer(String playername, String cityname) {
        new PlayerYAMLConfig(playername).create();
        new PlayerYAMLConfig(playername).write("City", cityname);
        if(!new PlayerYAMLConfig(playername).exist()) {
            new LWLogging().critical(new Language().get("liveweather.playerconfig.weather.cantcreate"));
        }
        return null;
    }
}
