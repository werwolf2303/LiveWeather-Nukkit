package com.liveweather.setter;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.Listener;
import com.liveweather.api.SetWeather;

public class Wetter implements Listener {
    public void setWeather(String apiresponse, Player p) {
        if(apiresponse.contains("Thunderstorm")) {
            new SetWeather().setThundering(p);
        }else{
            if(apiresponse.contains("Drizzle")) {
                new SetWeather().setRaining(p);
            }else{
                if(apiresponse.contains("Rain")) {
                    new SetWeather().setRaining(p);
                }else{
                    if(apiresponse.contains("Snow")) {
                        new SetWeather().setRaining(p);
                    }else{
                        if(apiresponse.contains("Clear")) {
                            new SetWeather().setClear(p);
                        }else{
                            if(apiresponse.contains("Clouds")) {
                                new SetWeather().setClear(p);
                            }
                        }
                    }
                }
            }
        }
    }
}
