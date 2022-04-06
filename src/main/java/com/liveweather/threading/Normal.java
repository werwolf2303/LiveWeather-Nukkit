package com.liveweather.threading;

import cn.nukkit.Server;
import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;

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
            new LWLogging().critical(new Language().get("liveweather.threading.critical"));
        }
    }
}
