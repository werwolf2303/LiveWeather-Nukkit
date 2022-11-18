package com.liveweather.debug;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.instances.InstanceManager;
import com.liveweather.utils.StringBuffer;

public class TriggerCommand extends Command {

    public TriggerCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        StringBuffer buffer = new StringBuffer();
        if(!(commandSender instanceof Player)) {
            try {
                if (strings[0].toLowerCase().contains("info")) {
                    buffer.add(strings);
                    buffer.remove(0);
                    InstanceManager.getLogger().normal(buffer.toString());
                }
                if (strings[0].toLowerCase().contains("debugging")) {
                    buffer.add(strings);
                    buffer.remove(0);
                    InstanceManager.getLogger().debugging(buffer.toString());
                }
                if (strings[0].toLowerCase().contains("error")) {
                    buffer.add(strings);
                    buffer.remove(0);
                    InstanceManager.getLogger().error(buffer.toString());
                }
                if (strings[0].toLowerCase().contains("extension")) {
                    buffer.add(strings);
                    buffer.remove(0);
                    InstanceManager.getLogger().extension(buffer.toString());
                }
                if (strings[0].toLowerCase().contains("warning")) {
                    buffer.add(strings);
                    buffer.remove(0);
                    InstanceManager.getLogger().warning(buffer.toString());
                }
                if (strings[0].toLowerCase().contains("critical")) {
                    buffer.add(strings);
                    buffer.remove(0);
                    InstanceManager.getLogger().critical(buffer.toString());
                }
            }catch (ArrayIndexOutOfBoundsException aioobe) {
            }
        }
        return false;
    }
}
