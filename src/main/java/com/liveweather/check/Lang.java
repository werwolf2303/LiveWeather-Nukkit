package com.liveweather.check;


import com.liveweather.storage.LWConfig;

public class Lang {
    public boolean isEn() {
        if(new LWConfig().read("language").toLowerCase().contains("en")) {
            return true;
        }else{
            return false;
        }
    }
}
