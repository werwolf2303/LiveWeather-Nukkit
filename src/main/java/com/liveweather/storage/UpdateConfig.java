package com.liveweather.storage;

import cn.nukkit.Server;
import com.liveweather.report.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UpdateConfig {
    public String config = Server.getInstance().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "updater.cfg";
    public UpdateConfig() {
        if(!new File(config).exists()) {
            try {
                new File(config).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void write(String date) {
        try {
            FileWriter myWriter = new FileWriter(config);
            myWriter.write(date);
            myWriter.close();
        } catch (IOException e) {
            new Report().create("Updater failed", "Failed to write to updater.cfg");
        }
    }
    public String read() {
        String out = "";
        try {
            File myObjs = new File(config);
            Scanner myReaders = new Scanner(myObjs);
            while (myReaders.hasNextLine()) {
                String data = myReaders.nextLine();
                out = data;
            }
            myReaders.close();
        } catch (FileNotFoundException es) {

        }
        return out;
    }
}
