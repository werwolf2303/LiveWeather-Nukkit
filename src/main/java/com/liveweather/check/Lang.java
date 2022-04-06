package com.liveweather.check;

import com.liveweather.storage.Options;

public class Lang {
    public boolean isEn() {
        if(new Options().getConfig("language").toLowerCase().contains("en")) {
            return true;
        }else{
            return false;
        }
    }
}
