package com.liveweather.storage;


import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Options {
    public String config = Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "options.cfg";
    public boolean createConfig() {
        try {
            return new File(config).createNewFile();
        } catch (IOException e) {
            new LWLogging().critical(new Language().get("liveweather.options.cantcreate"));
            return false;
        }
    }
    public boolean writeConfig(String name, String value) {
        String towrite = "";
        try {
            File myObjs = new File(config);
            Scanner myReaders = new Scanner(myObjs);
            while (myReaders.hasNextLine()) {
                String data = myReaders.nextLine();
                if (towrite.equals("")) {
                    towrite = data;
                } else {
                    towrite = towrite + "\n" + data;
                }
            }
            myReaders.close();
        } catch (FileNotFoundException es) {
            return false;
        }
        try {
            FileWriter myWriter = new FileWriter(config);
            myWriter.write(towrite + "\n" + name + ":" + value);
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
            new LWLogging().critical(new Language().get("liveweather.options.cantread"));
        }
        return "";
    }
}
