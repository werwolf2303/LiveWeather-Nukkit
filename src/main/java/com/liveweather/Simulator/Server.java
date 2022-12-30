package com.liveweather.simulator;

import cn.nukkit.Player;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.lang.BaseLang;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.utils.MainLogger;

import java.util.*;

public class Server extends cn.nukkit.Server {
    int maxplayers = 0;
    final ArrayList<cn.nukkit.Player> players = new ArrayList<>();

    public Server() {
        super();
    }
    @Override
    public String getName() {
        return "DebugServer";
    }

    @Override
    public MainLogger getLogger() {
        return new Logger();
    }

    @Override
    public int getMaxPlayers() {
        return maxplayers;
    }

    @Override
    public String getPluginPath() {
        return "DebugEnv/plugins/";
    }

    @Override
    public String getFilePath() {
        return "DebugEnv/";
    }

    @Override
    public BaseLang getLanguage() {
        return new BaseLang(BaseLang.FALLBACK_LANGUAGE);
    }

    @Override
    public PluginManager getPluginManager() {
        return new com.liveweather.simulator.PluginManager(null,null);
    }

    @Override
    public SimpleCommandMap getCommandMap() {
        return new CommandMap();
    }

    @Override
    public ServerScheduler getScheduler() {
        return new com.liveweather.simulator.ServerScheduler();
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        new SimulatorLogger().info("Set MaxPlayers to: " + maxPlayers);
        maxplayers = maxPlayers;
    }

    @Override
    public void addOnlinePlayer(Player player) {
        players.add(player);
    }

    @Override
    public Map<UUID, Player> getOnlinePlayers() {
        HashMap<UUID, Player> h = new HashMap<>();
        h.put(new UUID(0,0), players.get(0));
        return h;
    }

    @Override
    public Player getPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void broadcastPacket() {
        new SimulatorLogger().info("Broadcast Packet");
    }
}
