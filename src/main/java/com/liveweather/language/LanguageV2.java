package com.liveweather.language;

import cn.nukkit.Server;
import com.liveweather.storage.YAMLConfig;
import com.liveweather.translate.Languages;
import com.liveweather.translate.Translate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Deprecated
public class LanguageV2 {
    String location = Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/lang";
    String EN = "/en.cfg";
    File en = new File(location + EN);
    public String get(String obj) {
        String toreturn = "";
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
        Languages l = Languages.getInstance();
        return Translate.translate(toreturn, l.ENGLISH,new YAMLConfig().read("language"));
    }
}
