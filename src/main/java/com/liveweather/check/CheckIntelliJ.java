package com.liveweather.check;

import java.io.File;

public class CheckIntelliJ {
    public boolean check() {
        boolean toreturn = false;
        if(new File(System.getProperty("user.dir") + "\\pom.xml").exists()) {
            toreturn = true;
        }
        return toreturn;
    }
}
