package com.liveweather.utils;

import com.liveweather.instances.InstanceManager;

import java.io.InputStream;
import java.util.Scanner;

public class Resources {
    public String read(String path) {
        if(!path.startsWith("/")) {
            InstanceManager.getLogger().error("Illegal Path Exception in Resources().read() : Expected path to start with '/'");
        }
        try {
            Scanner s = new Scanner(getFileFromResourceAsStream(path)).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return path;
        }
    }
    public InputStream getFileFromResourceAsStream(String fileName) throws IllegalArgumentException {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        if (inputStream == null) {
            InstanceManager.getLogger().error("Failed to read from resources: inputStream == null");
            return null;
        } else {
            return inputStream;
        }
    }
}
