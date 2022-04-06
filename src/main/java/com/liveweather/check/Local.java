package com.liveweather.check;

import cn.nukkit.Player;

public class Local {
    public boolean isLocal(Player p) {
        if(p.getAddress().toLowerCase().contains("localhost")) {
            return true;
        }else{
            if(p.getAddress().toLowerCase().contains("127.0.0.1")) {
                return true;
            }else{
                return false;
            }
        }
    }
}
