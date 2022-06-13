package com.liveweather.language;

import cn.nukkit.Server;
import cn.nukkit.utils.Logger;
import com.liveweather.check.Lang;
import com.liveweather.commandline.LWLogging;
import com.liveweather.storage.Options;
import com.liveweather.translate.Languages;
import com.liveweather.translate.Translate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Language {
    String location = Server.getInstance().getFilePath() + "/plugins/" + "LiveWeather" + "/" + "jarfile/lang";
    String DE = "/de.cfg";
    String EN = "/en.cfg";
    String CHS = "/chs.cfg";
    File de = new File(location + DE);
    File en = new File(location + EN);
    File chs = new File(location + CHS);
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
            if(new Options().getConfig("language").toLowerCase().equals("chs")) {
                try (FileInputStream fis = new FileInputStream(chs);
                     InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(isr)
                ) {

                    String str;
                    while ((str = reader.readLine()) != null) {
                        if(str.contains(obj)) {
                            toreturn = str.replace(obj + ":", "");
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    Languages l = Languages.getInstance();
                    Scanner myReader = new Scanner(en);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        if (data.contains(obj)) {
                            toreturn = Translate.translate(data.replace(obj + ":", "").replace("[LiveWeather]", ""),l.ENGLISH,new Options().getConfig("language"));
                            toreturn = "[LiveWeather] " + toreturn;
                            break;
                        }
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    return obj;
                }
            }
        }
        if(!toreturn.equals("")) {
            return toreturn;
        }else{
            return obj;
        }
    }
}
