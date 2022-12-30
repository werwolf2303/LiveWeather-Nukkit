package com.liveweather.utils;

import com.liveweather.commandline.LWLogging;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {
    @SuppressWarnings("StringConcatenationInLoop")
    public String getContent(String path) {
        String toret = "";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(toret.equals("")) {
                    toret = data;
                }else{
                    toret = toret + "\n" + data;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            new LWLogging().critical("In (FileUtils) getContent : " + e.getMessage());
        }
        return toret;
    }
}
