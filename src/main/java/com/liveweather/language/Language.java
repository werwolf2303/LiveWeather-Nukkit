package com.liveweather.language;

import cn.nukkit.Server;
import com.liveweather.check.Lang;
import com.liveweather.storage.Options;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Language {
    String location = Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/lang";
    String DE = "/de.cfg";
    String EN = "/en.cfg";
    File de = new File(location + DE);
    File en = new File(location + EN);
    public String get(String obj) {
        String toreturn = "";
        if(new Options().getConfig("language").toLowerCase().equals("en")) {
            try {
                Scanner myReader = new Scanner(en);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.contains(obj)) {
                        toreturn = data.replace(obj + ":", "");
                        break;
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                return obj;
            }
        }else{
            try {
                Scanner myReader = new Scanner(de);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.contains(obj)) {
                        toreturn = data.replace(obj + ":", "");
                        break;
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                return obj;
            }
        }
        if(!toreturn.equals("")) {
            return toreturn;
        }else{
            return obj;
        }
    }
}
