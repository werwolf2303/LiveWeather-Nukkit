package com.liveweather.check;

import cn.nukkit.Player;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class Local {
    public boolean isLocal(Player p) {
        return p.getAddress().toLowerCase().contains("localhost") || p.getAddress().toLowerCase().contains("127.0.0.1");
    }
}
