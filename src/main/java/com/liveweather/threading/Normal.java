package com.liveweather.threading;

import com.liveweather.commandline.LWLogging;
import com.liveweather.language.Language;

@SuppressWarnings({"deprecation", "unused"})
public class Normal {
    final Thread t;
    int prio = 8;
    boolean running = false;
    boolean actual = false;
    public Normal(Runnable run) {
        t = new Thread(run);
    }
    public void setPriority(int priority) {
        if(!actual) {
            prio = priority;
        }
    }
    public int getPriority() {
        return prio;
    }
    public void start() {
        t.setPriority(prio);
        t.start();
        if(t.isAlive()) {
            running = true;
            actual = true;
        }else{
            new LWLogging().critical(new Language().get("liveweather.threading.critical"));
        }
    }
    public boolean isRunning() {
        return running;
    }
    public void stop() {
        if(actual) {
            t.stop();
        }
    }
}
