package com.liveweather.storage;

import cn.nukkit.Server;

import java.io.*;
import java.util.Properties;

public class PlayerConfig {
    File playerloc = new File(Server.getInstance().getPluginPath().replace("\\", "/") + "LiveWeather/player.properties");
    Properties properties;
    public PlayerConfig() {
        if(!playerloc.exists()) {
            create();

        }else{
            properties = new Properties();
            try {
                properties.load(new FileInputStream(playerloc));
            } catch (IOException e) {
            }
        }
    }
    public void create() {
        try {
            playerloc.createNewFile();
        } catch (IOException e) {
        }
    }
    public String read(String player) {
        return properties.getProperty(player);
    }
    public void write(String player, String city) {
        properties.setProperty(player,city);
        OutputStream out = null;
        try {
            out = new FileOutputStream(playerloc);
        } catch (FileNotFoundException e) {
        }
        try {
            properties.store(out, null);
        } catch (IOException e) {
        }
    }
    public void change(String player, String city) {
        properties.remove(player);
        properties.setProperty(player,city);
        OutputStream out = null;
        try {
            out = new FileOutputStream(playerloc);
        } catch (FileNotFoundException e) {
        }
        try {
            properties.store(out, null);
        } catch (IOException e) {
        }
    }
    public void delete(String player) {
        properties.remove(player);
        OutputStream out = null;
        try {
            out = new FileOutputStream(playerloc);
        } catch (FileNotFoundException e) {
        }
        try {
            properties.store(out, null);
        } catch (IOException e) {
        }
    }
    public boolean hasEntered(String player) {
        return !read(player).equals("");
    }
    public void changePlayer(String player, String city) {
        delete(player);
        write(player,city);
    }
    public void deletePlayer(String player) {
        delete(player);
    }
    public String getCity(String player) {
        return read(player);
    }
}
