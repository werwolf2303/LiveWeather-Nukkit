package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.api.GetWeather;
import com.liveweather.language.Language;
import com.liveweather.storage.PlayerConfigs3;
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
                    .addInput(new Language().get("liveweather.forms.button"));
            form.setTitle(new Language().get("liveweather.forms.title"));
            form.send(p, (targetPlayer, targetForm, data) -> {
                if(data == null) return; //Если форма закрыта принудительно, то data будет иметь значение null
                //new PlayerConfigs().writeConfig(event.getPlayer().getName(), targetForm.getElements().toString());
                if(new GetWeather().isValid(data.toString().replace("[", "").replace("]", ""))) {
                    new PlayerConfigs3().createPlayer(p.getName(), data.toString().replace("[", "").replace("]", ""));
                    targetPlayer.sendMessage(new Language().get("liveweather.commands.citysetter.success") + data.toString().replace("[", "").replace("]", ""));
                }else{
                    targetPlayer.sendMessage(new Language().get("liveweather.commands.citysetter.notvalid"));
                }
                //Server.getInstance().getLogger().info(targetForm.getElements().toString());
            });
        }else{
            commandSender.sendMessage(new Language().get("liveweather.commands.server"));
        }
        return false;
    }
}
