package com.liveweather.storage;

import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Configuring {
    public String config = Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "config.cfg";
    public boolean createConfig() {
        try {
            return new File(config).createNewFile();
        } catch (IOException e) {
            new LWLogging().critical("Error cant create config");
            return false;
        }
    }
    public boolean writeConfig(String name, String value) {
        try {
            FileWriter myWriter = new FileWriter(config);
            myWriter.write(name + ":" + value);
            myWriter.close();
        } catch (IOException e) {
            return false;
        }
        try {
            File myObj = new File(config);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(name)) {
                    return true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }
    public String getConfig(String name) {
        try {
            File myObj = new File(config);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(name)) {
                    return data.replace(name + ":", "");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            new LWLogging().critical("Failed to read value in config");
        }
        return null;
    }
}
