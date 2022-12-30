package com.liveweather.storage;

import com.liveweather.Initiator;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;
import com.liveweather.utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("unused")
public class LWFileLogger {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public LWFileLogger() {
        if(!new File(Initiator.pluginlocation + "/log.txt").exists()) {
            try {
                new File(Initiator.pluginlocation + "/log.txt").createNewFile();
            } catch (IOException e) {
                new LWLogging().error(new Language().get("liveweather.logfile.cantwrite"));
            }
        }
    }
    public void write(String message) {
        String before = new FileUtils().getContent(Initiator.pluginlocation + "/log.txt");
        try {
            FileWriter writer = new FileWriter(Initiator.pluginlocation + "/log.txt");
            writer.write(before+"\n"+message);
            writer.close();
        } catch (IOException e) {
            new LWLogging().error(new Language().get("liveweather.logfile.cantcreate"));
        }

    }
}
