package com.liveweather.simulator;

import cn.nukkit.scheduler.TaskHandler;

@SuppressWarnings("deprecation")
public class ServerScheduler extends cn.nukkit.scheduler.ServerScheduler {
    @Override
    public TaskHandler scheduleRepeatingTask(Runnable task, int period) {
        return new com.liveweather.simulator.TaskHandler().simulateRepeatingTask(task, period);
    }
}
