package com.liveweather.Simulator;

import cn.nukkit.Server;
import cn.nukkit.level.format.LevelProvider;

public class Level extends cn.nukkit.level.Level {
    public Level() {
        super();
    }

    @Override
    public boolean setRaining(boolean raining) {
        new SimulatorLogger().info("Level DefaultWorld set Thunder");
        return true;
    }

    @Override
    public boolean setThundering(boolean thundering) {
        new SimulatorLogger().info("Level DefaultWorld set Thunder");
        return true;
    }


}
