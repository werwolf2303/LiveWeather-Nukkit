package com.liveweather.updater;

import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import com.liveweather.Initiator;
import com.liveweather.client.Client;
import com.liveweather.commandline.LWLogging;
import com.liveweather.report.Report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.liveweather.commandline.LWLogging.unregisterPlugin;

public class Update {
    String getCurrent() {
        return Initiator.plugin.getDescription().getVersion();
    }
    String getNewest() {
        String f = "";
        Client client = new Client();
        String output = client.get("https://cloudburstmc.org/resources/liveweather.792/");
        String[] conv1 = output.split("<");
        for(String s : conv1) {
            if(s.contains("class=\"u-muted\"")) {
                f=s.replace("span class=\"u-muted\">", "");
                break;
            }
        }
        if(f.equals("")) {
            new Report().create("Failed to get newest version", output);
        }
        return f;
    }
    public boolean isNewerAvailable() {
        if (Integer.parseInt(getCurrent().replace(".", "")) > Integer.parseInt(getNewest().replace(".", ""))) {
            return false;
        } else {
            if (getNewest().equals(getCurrent())) {
                return false;
            } else {
                return true;
            }
        }
    }
    public String pluginfolder = Server.getInstance().getFilePath() + "/" + "plugins";
    @Deprecated
    public void downloadNewestJar() {
        Initiator.doUpdate = true;
        Client client = new Client();
        String output = client.get("https://cloudburstmc.org/resources/liveweather.792/download");
        try {
            FileWriter writer = new FileWriter(new File(pluginfolder + "/LiveWeather-Nukkit_Update.jar"));
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Plugin plugin = Server.getInstance().getPluginManager().getPlugin("LiveWeather");
        unregisterPlugin(plugin);
        Server.getInstance().getPluginManager().disablePlugin(plugin);
    }
}
