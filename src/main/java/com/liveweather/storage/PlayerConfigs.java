package com.liveweather.storage;

import cn.nukkit.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlayerConfigs {
    public File config = new File(Server.getInstance().getFilePath() + "/plugins" + "/" + "LiveWeather" + "/" + "locations.txt");
    public File pluginfolder = new File(Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather");
    public boolean createPluginFolder() {
        pluginfolder.mkdir();
        return pluginfolder.exists();
    }
    public boolean createConfig() {
        try {
            config.createNewFile();
            return config.exists();
        } catch (IOException e) {
            return false;
        }
    }
    public boolean hasPlayerEnteredCityName(String playername) {
        try {
            Scanner myReader = new Scanner(config);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(playername)) {
                    return true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }
    public void writeConfig(String playername, String value) {
        String values = "";
        try {
            Scanner myReader = new Scanner(config);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(playername)) {
                    data = playername + ":" + value;
                }
                if(values.equals("")) {
                    values = data;
                }else{
                    values = "\n" + data;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
        }
        try {
            FileWriter myWriter = new FileWriter(config);
            if(values.equals("")) {
                myWriter.write(playername + ":" + value);
            }else{
                myWriter.write(values + "\n" + playername + ":" + value);
            }
            myWriter.close();
        } catch (IOException e) {
        }
    }
    public String getPlayerCity(String playername) {
        String returns = "";
        try {
            Scanner myReader = new Scanner(config);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(playername)) {
                    returns = data.replace(playername + ":", "");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
        }
        return returns;
    }
}
