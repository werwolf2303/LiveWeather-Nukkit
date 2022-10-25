package com.liveweather.storage;

import com.liveweather.instances.InstanceManager;

import java.io.*;
import java.util.Properties;

public class LWConfig {
    public static String config = InstanceManager.getServer().getFilePath() + "/" + "plugins" + "/" + "LiveWeather" + "/" + "options.properties";
    File configFile = new File(config);
    public LWConfig() {
        if(!configFile.exists()) {
            try {
                Properties props = new Properties();
                FileWriter writer = new FileWriter(configFile);
                props.store(writer, "LWConfig");
                writer.close();
            } catch (FileNotFoundException ex) {
                // file does not exist
            } catch (IOException ex) {
                // I/O error
            }
        }
    }
    public String read(String key) {
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            if(props.containsKey(key)) {
                return props.getProperty(key);
            }else{
                return key;
            }
        }catch (FileNotFoundException e) {
            return key;
        } catch (IOException e) {
            return key;
        }
    }
    public void delete(String key) {
        try {
            Properties props = new Properties();
            props.load(new FileReader(configFile));
            FileWriter writer = new FileWriter(configFile);
            props.remove(key);
            props.store(writer, "LWConfig");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }
    public void write(String key, String value) {
        try {
            Properties props = new Properties();
            props.load(new FileReader(configFile));
            props.setProperty(key, value);
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "LWConfig");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }
}
