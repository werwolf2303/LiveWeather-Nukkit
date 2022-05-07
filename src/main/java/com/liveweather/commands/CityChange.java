package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.language.Language;
import com.liveweather.storage.Options;
import com.liveweather.storage.PlayerConfigs;
import com.liveweather.storage.PlayerConfigs2;
import ru.nukkitx.forms.elements.CustomForm;

public class CityChange extends Command {
    public CityChange(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(new PlayerConfigs2().hasEntered(p.getName())) {
                CustomForm form = new CustomForm()
                        .addInput(new Language().get("liveweather.forms.button"));
                form.setTitle(new Language().get("liveweather.forms.title"));
                form.send(p, (targetPlayer, targetForm, data) -> {
                    if(data == null) return; //Если форма закрыта принудительно, то data будет иметь значение null
                    //new PlayerConfigs().writeConfig(event.getPlayer().getName(), targetForm.getElements().toString());
                    new PlayerConfigs2().changePlayer(p.getName(), data.toString().replace("[", "").replace("]", ""));
                    //Server.getInstance().getLogger().info(targetForm.getElements().toString());
                    targetPlayer.sendMessage(new Language().get("liveweather.commands.citychange.successfull") + data.toString().replace("[", "").replace("]", ""));
                });
            }else{
                p.sendMessage(new Language().get("liveweather.commands.citychange.dontentered"));
            }
        }else{
            commandSender.sendMessage(new Language().get("liveweather.commands.server"));
        }
        return false;
    }
}
