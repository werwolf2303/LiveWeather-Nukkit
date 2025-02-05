package com.liveweather;

import com.liveweather.config.Config;
import com.liveweather.language.libLanguage;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        PublicValues.pluginpath = new File("Server/plugins/LiveWeather");
        PublicValues.configPath = new File(PublicValues.pluginpath, "config.yml");
        PublicValues.config = new Config();
        PublicValues.language = new libLanguage();
        PublicValues.language.setLanguageFolder("lang");
        PublicValues.language.setNoAutoFindLanguage("en");

        System.out.println(MessageConstructor.fromTranslation("liveweather.commands.citydelete.didntenter"));
    }
}
