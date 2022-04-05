package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.api.GetWeather;
import com.liveweather.storage.PlayerConfigs;
import com.liveweather.storage.PlayerConfigs2;
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
                if(new GetWeather().isValid(data.toString().replace("[", "").replace("]", ""))) {
                    new PlayerConfigs2().createPlayer(p.getName(), data.toString().replace("[", "").replace("]", ""));
                    targetPlayer.sendMessage("Successfull entered city " + data.toString().replace("[", "").replace("]", ""));
                }else{
                    targetPlayer.sendMessage("Invalid City or not supported by the api");
                }
                //Server.getInstance().getLogger().info(targetForm.getElements().toString());
            });
        }
        return false;
    }
}
