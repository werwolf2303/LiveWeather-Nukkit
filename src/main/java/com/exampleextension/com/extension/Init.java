package com.exampleextension.com.extension;

import com.liveweather.extensions.Extension;

public class Init implements Extension {
    public void onLoad() {
        //Called when loading
    }

    public String getName() {
        //Define here the name of your extension
        return "Example Extension";
    }
    public void onDisable() {
        //Called when nukkit is stopping or reloading
    }
}
