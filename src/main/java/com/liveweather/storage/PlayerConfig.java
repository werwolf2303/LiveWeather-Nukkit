package com.liveweather.storage;

import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.threading.Normal;

import java.io.*;
import java.util.Properties;

public class PlayerConfig {
    String location = Server.getInstance().getPluginPath().replace("\\", "/") + "LiveWeather/PlayerCity/";
    public PlayerConfig() {
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
            FileReader reader= null;
            String filename = location + playername + ".properties";
            try {
                reader = new FileReader(filename);
            } catch (FileNotFoundException e) {
            }

            Properties p=new Properties();
            try {
                p.load(reader);
            } catch (IOException e) {
            }
            return p.get("City").toString();
        }catch (NullPointerException exc) {
            new LWLogging().critical(new Language().get("liveweather.playerconfig.weather.cantget"));
        }
        return "InvalidCity";
    }
    public boolean hasEntered(String playername) {
        try {
            FileReader reader = null;
            String filename = location + playername + ".properties";
            try {
                reader = new FileReader(filename);
            } catch (FileNotFoundException e) {
                return false;
            }

            Properties p = new Properties();
            try {
                p.load(reader);
            } catch (IOException e) {
                return false;
            }
            return !p.get("City").equals("");
        }catch (NullPointerException ex) {
            return false;
        }
    }
    Runnable deleteplayer(String playername) {
        FileReader reader= null;
        String filename = location + playername + ".properties";
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
        }

        Properties p=new Properties();
        try {
            p.load(reader);
        } catch (IOException e) {
        }
        p.remove("City");
        try {
            p.store(new FileWriter(filename), "Player config");
        } catch (IOException e) {
        }
        return null;
    }
    Runnable changeplayer(String playername, String city) {
        FileReader reader= null;
        String filename = location + playername + ".properties";
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
        }

        Properties p=new Properties();
        try {
            p.load(reader);
        } catch (IOException e) {
        }
        p.remove("City");
        p.setProperty("City", city);
        try {
            p.store(new FileWriter(filename), "Player config");
        } catch (IOException e) {
        }
        return null;
    }
    Runnable createplayer(String playername, String cityname) {
        FileReader reader= null;
        String filename = location + playername + ".properties";
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
        }
        if(!new File(filename).exists()) {
            try {
                new File(filename).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                new LWLogging().debugging(filename);
            }
        }
        Properties p=new Properties();
        p.setProperty("City", cityname);
        try {
            p.store(new FileWriter(filename), "Player config");
        } catch (IOException e) {
        }
        if(new PlayerConfig().getCity(playername).equals("")) {
            new LWLogging().critical(new Language().get("liveweather.playerconfig.weather.cantcreate"));
        }
        return null;
    }
}
