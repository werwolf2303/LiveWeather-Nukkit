package com.liveweather.storage;

import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.threading.Normal;

import java.io.File;
import java.io.IOException;

public class PlayerConfigs2 {
    public File pluginfolder = new File(Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather");
    String pluginfolderpath = Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather";
    public void createPluginFolder() {
        new Normal(createpluginfolder()).start();
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
            return new File(pluginfolderpath + "/" + playername).list()[0];
        }catch (NullPointerException exc) {
            new LWLogging().critical("Cant get weather for player");
        }
        return "NULL";
    }
    public boolean hasEntered(String playername) {
        return new File(pluginfolderpath + "/" + playername).exists();
    }
    Runnable deleteplayer(String playername) {
        new File(pluginfolderpath + "/" + playername).delete();
        if(new File(pluginfolderpath + "/" + playername).exists()) {
            new LWLogging().critical("Cant delete player");
        }
        return null;
    }
    Runnable changeplayer(String playername, String city) {
        new File(pluginfolderpath + "/" + playername).delete();
        new File(pluginfolderpath + "/" + playername).mkdir();
        new File(pluginfolderpath + "/" + playername + "/" + city);
        return null;
    }
    Runnable createpluginfolder() {
        pluginfolder.mkdir();
        return null;
    }
    Runnable createplayer(String playername, String cityname) {
        new File(pluginfolderpath + "/" + playername).mkdir();
        try {
            new File(pluginfolderpath + "/" + playername + "/" + cityname).createNewFile();
        } catch (IOException e) {
            new LWLogging().critical("cant create player");
        }
        return null;
    }
}
