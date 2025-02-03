package com.liveweather;

import com.liveweather.config.Config;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        PublicValues.pluginpath = new File("data");
        PublicValues.configPath = new File(PublicValues.pluginpath, "config.json");
        PublicValues.config = new Config();

    }
}
