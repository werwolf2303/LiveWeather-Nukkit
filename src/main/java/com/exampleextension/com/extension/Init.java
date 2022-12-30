package com.exampleextension.com.extension;
@SuppressWarnings("ALL")
public class Init {
    public void onLoad() {
        //REQUIRED: Called when loading
    }
    public String getExtensionName() {
        //REQUIRED Define here the name of your extension
        return "Example Extension";
    }
    public void onDisable() {
        //REQUIRED: Called when nukkit is stopping or reloading
    }

    public String dumpFunctions() {
        //OPTIONAL: When you want to dump all functions to console
        return "true";
    }
}
