package com.liveweather.threading;

import cn.nukkit.Server;

public class Normal {
    Thread t = new Thread();
    int prio = 8;
    boolean running = false;
    public Normal(Runnable run) {
        t = new Thread(run);
    }
    public void setPriority(int priority) {
        prio = priority;
    }
    public int getPriority() {
        return prio;
    }
    public void start() {
        t.setPriority(prio);
        t.start();
        if(t.isAlive()) {
            running = true;
        }else{
            Server.getInstance().getLogger().error("LiveWeather :: Critical Error in com.liveweather.threading.Normal");
        }
    }
}
