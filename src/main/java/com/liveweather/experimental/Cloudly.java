package com.liveweather.experimental;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;

public class Cloudly {
    //Activate this with 'cloudly:true' in options.cfg
    @Deprecated
    public void create(Player p) {
        p.setViewDistance(2);
    }
    public Cloudly() {
        init();
    }
    public boolean fogenabled = false;
    boolean failure = false;
    void init() {
        fogenabled = true;
    }
    public void setFog(Player p, int distance) {
        if(p.getLevel().getDimension()>0) {
        }else{
            if (distance < 0) {
                failure = true;
            } else {
                if (distance == 0) {
                    failure = true;
                }
            }
            if (failure) {
                new LWLogging().error("Cloudly --> Zero or Minus not allowed");
            } else {
                if(distance==10) {
                    p.setViewDistance(Server.getInstance().getViewDistance());
                }else{
                    p.setViewDistance(distance);
                }
            }
        }
    }
}
