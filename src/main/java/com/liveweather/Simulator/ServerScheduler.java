package com.liveweather.Simulator;

import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;

public class ServerScheduler extends cn.nukkit.scheduler.ServerScheduler {
    @Override
    public TaskHandler scheduleRepeatingTask(Runnable task, int period) {
        return new com.liveweather.Simulator.TaskHandler().simulateRepeatingTask(task, period);
    }
}
