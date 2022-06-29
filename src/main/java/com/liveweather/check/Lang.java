package com.liveweather.check;

import com.liveweather.storage.YAMLConfig;

public class Lang {
    public boolean isEn() {
        if(new YAMLConfig().read("language").toLowerCase().contains("en")) {
            return true;
        }else{
            return false;
        }
    }
}
