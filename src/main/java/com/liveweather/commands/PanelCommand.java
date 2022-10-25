package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.panel.AdminPanel;

public class PanelCommand extends Command {
    public PanelCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            new AdminPanel().open((Player) commandSender);
        }
        return false;
    }
}
