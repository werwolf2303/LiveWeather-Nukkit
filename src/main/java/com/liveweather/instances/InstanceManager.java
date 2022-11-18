package com.liveweather.instances;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import com.liveweather.GlobalValues;
import com.liveweather.Initiator;
import com.liveweather.Logger;
import com.liveweather.Simulator.APIResponse;
import com.liveweather.Simulator.Console;
import com.liveweather.api.GetWeather;
import com.liveweather.commandline.LWLogging;
import com.liveweather.debug.DebugLogger;
import com.liveweather.formapi.forms.elements.ModalForm;
import com.liveweather.utils.FileUtils;

public class InstanceManager {
    static com.liveweather.Simulator.Server server = null;
    static DebugLogger logger = null;
    static GetWeather weather = null;
    static Console console = null;
    public static Server getServer() {
        if(GlobalValues.debug) {
            if(server==null) {
                server = new com.liveweather.Simulator.Server();
            }
            return server;
        }
        return Server.getInstance();
    }
    public static GetWeather getWeather() {
        if(GlobalValues.debug) {
            if(weather==null) {
                weather = new APIResponse();
            }
        }
        return weather;
    }
    public static DebugLogger getDebugLogger() {
        if(logger==null) {
            logger = new DebugLogger();
        }
        return logger;
    }
    static Logger log = null;
    public static Logger getLogger() {
        if(GlobalValues.serverdebug) {
            log = new DebugLogger();
        }else{
            log = new LWLogging();
        }
        return log;
    }
    public static Console getConsole() {
        if(console!=null) {
            return console;
        }else{
            return null;
        }
    }
    public static void setConsole(Console cons) {
        console = cons;
    }
    public static ModalForm getModalForm(String title, String content, String falsebutton, String truebutton) {
        if(GlobalValues.debug) {
            return new com.liveweather.Simulator.forms.ModalForm(title, content, falsebutton, truebutton);
        }else{
            return new ModalForm(title, content, falsebutton, truebutton);
        }
    }
}
