package com.liveweather.threading;

import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;

public class High {
    Thread t = new Thread();
    int prio = Thread.MAX_PRIORITY;
    public boolean running = false;
    public High(Runnable run) {
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
    public void stop() {
        if(running) {
            t.stop();
        }
    }
}
