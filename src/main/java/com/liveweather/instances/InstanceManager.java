package com.liveweather.instances;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import com.liveweather.GlobalValues;
import com.liveweather.Simulator.APIResponse;
import com.liveweather.Simulator.Console;
import com.liveweather.Simulator.DebugFrame;
import com.liveweather.api.GetWeather;
import com.liveweather.formapi.forms.elements.ModalForm;

public class InstanceManager {
    static com.liveweather.Simulator.Server server = null;
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
    static DebugFrame.Content frame = null;

    public static DebugFrame.Content getDebugFrame() {
        return frame;
    }
    public static void setDebugFrame(DebugFrame.Content content) {
        frame = content;
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
