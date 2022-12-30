package com.liveweather.extensions;

@SuppressWarnings({"unused", "JavadocBlankLines"})
public interface Extension {
    /**
     * Extension Creator info:
     *
     * For example look at com.exampleextension
     *
     *
     * Please use this package path for your main method: com.extension.Init
     *
     * Use these methods for your extension
     *
     * onLoad : Called when loading your plugin
     * onDisable : Called when reloading or disabling your plugin
     *
     *
     * Compile your extension with dependencies
     *
     * Important : LiveWeather and Nukkit
     *
     * Support is limited!! Use at your own risk!!!!!!
     *
     */

    void onLoad();
    void onDisable();

    void dumpFunctions();
    String getExtensionName();
}
