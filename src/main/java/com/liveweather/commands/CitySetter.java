package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.storage.PlayerConfigs;
import ru.nukkitx.forms.elements.CustomForm;

public class CitySetter extends Command {
    public CitySetter(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            CustomForm form = new CustomForm()
                    .addInput("Enter City");
            form.setTitle("Enter the City you live in");
            form.send(p, (targetPlayer, targetForm, data) -> {
                if(data == null) return; //Если форма закрыта принудительно, то data будет иметь значение null
                //new PlayerConfigs().writeConfig(event.getPlayer().getName(), targetForm.getElements().toString());
                new PlayerConfigs().writeConfig(p.getName(), data.toString().replace("[", "").replace("]", ""));
                //Server.getInstance().getLogger().info(targetForm.getElements().toString());
                targetPlayer.sendMessage("Succesfull entered city " + data.toString().replace("[", "").replace("]", ""));
            });
        }
        return false;
    }
}
