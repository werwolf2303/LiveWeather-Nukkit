package com.liveweather.language;

import cn.nukkit.Server;
import com.liveweather.storage.YAMLConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Language {
    String location = Server.getInstance().getFilePath().replace("\\", "/") + "plugins/" + "LiveWeather" + "/" + "jarfile/lang/";
    String language = new YAMLConfig().read("language");
    public String get(String obj) {
        String toreturn = "";
        try {
            Scanner myReader = new Scanner(new File(location + language + ".cfg"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains(obj)) {
                    toreturn = data.replace(obj + ":", "");
                    break;
                }
            }
            myReader.close();
            if (toreturn.equals("")) {
                return obj;
            } else {
                return toreturn;
            }
        }catch (FileNotFoundException ex) {
            return obj;
        }
    }
}
