package com.liveweather.simulator;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("SameReturnValue")
public class TaskHandler extends cn.nukkit.scheduler.TaskHandler {
    public TaskHandler() {
        super();
    }

    public cn.nukkit.scheduler.TaskHandler simulateRepeatingTask(Runnable run, int period) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                run.run();
            }
        }, 0, period);
        return null;
    }
}
