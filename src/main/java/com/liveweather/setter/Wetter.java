package com.liveweather.setter;

import cn.nukkit.Server;
import cn.nukkit.event.Listener;

public class Wetter implements Listener {
    public void setWeather(String apiresponse) {
        if(apiresponse.contains("Thunderstorm")) {

        }else{
            if(apiresponse.contains("Drizzle")) {

            }else{
                if(apiresponse.contains("Rain")) {

                }else{
                    if(apiresponse.contains("Snow")) {

                    }else{
                        if(apiresponse.contains("Clear")) {

                        }else{
                            if(apiresponse.contains("Clouds")) {

                            }
                        }
                    }
                }
            }
        }
    }
}
