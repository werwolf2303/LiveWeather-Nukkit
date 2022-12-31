package com.liveweather.check;

import com.liveweather.instances.InstanceManager;

public class PowerNukkit {
    public boolean isIt() {
        return InstanceManager.getServer().getCodename().equalsIgnoreCase("PowerNukkit");
    }
}
