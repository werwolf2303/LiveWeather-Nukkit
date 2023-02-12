package com.liveweather.threading;

import com.liveweather.commandline.LWLogging;
import com.liveweather.instances.InstanceManager;
import com.liveweather.language.Language;

@SuppressWarnings({"deprecation", "unused"})
public class High {
    final Thread t;
    int prio = Thread.MAX_PRIORITY;
    boolean running = false;
    public High(Runnable run) {
        t = new Thread(run);
    }
    public void setPriority(int priority) {
        if(!running) {
            prio = priority;
        }
    }
    public int getPriority() {
        return prio;
    }
    public void start() {
        t.setPriority(prio);
        InstanceManager.getThreadManager().add(t);
        t.start();
        if(t.isAlive()) {
            running = true;
        }else{
            new LWLogging().critical(new Language().get("liveweather.threading.critical"));
        }
    }
    public boolean isRunning() {
        return running;
    }
    public void stop() {
        if(running) {
            t.stop();
        }
    }
}
