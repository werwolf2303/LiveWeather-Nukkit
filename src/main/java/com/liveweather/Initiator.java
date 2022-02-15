package com.liveweather;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import com.liveweather.api.GetWeather;
import com.liveweather.setter.Wetter;
import com.liveweather.test.TestCommand;

public class Initiator extends PluginBase {
    int t = 0;
    @Override
    public void onLoad() {
        Server.getInstance().getLogger().info("LiveWeather init");
        Server.getInstance().getCommandMap().register("help", new TestCommand("weathertest", "Test weather set"));
        getServer().getScheduler().scheduleRepeatingTask(new Runnable() {
            @Override
            public void run() {
                if(t == 120) {
                    new Wetter().setWeather(new GetWeather().getWeather("Hemsbach"));
                    t=0;
                }else{
                    t=t+1;
                }
            }
        },1);
    }
    public Server server() {
        return getServer();
    }
}
