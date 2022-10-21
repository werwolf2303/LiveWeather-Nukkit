package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.api.GetWeather;
import com.liveweather.commandline.LWLogging;
import com.liveweather.formapi.forms.elements.CustomForm;
import com.liveweather.language.Language;
import com.liveweather.storage.PlayerConfig;
import com.liveweather.storage.PlayerConfigs3;

public class CitySetter extends Command {
    public CitySetter(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player p = (Player) commandSender;
            if(strings.length>0) {
                if (new GetWeather().isValid(strings[0].toString().replace("[", "").replace("]", ""))) {
                    new PlayerConfigs3().createPlayer(p.getName(), strings[0].toString().replace("[", "").replace("]", ""));
                    p.sendMessage(new Language().get("liveweather.commands.citysetter.success").replace("[Name]", strings[0].toString().replace("[", "").replace("]", "")));
                } else {
                    p.sendMessage(new Language().get("liveweather.commands.citysetter.notvalid"));
                }
            }else {
                if (!new PlayerConfig().hasEntered(p.getName())) {
                    CustomForm form = new CustomForm()
                            .addInput(new Language().get("liveweather.formapi.button"));
                    form.setTitle(new Language().get("liveweather.formapi.title"));
                    form.send(p, (targetPlayer, targetForm, data) -> {
                        if (data == null) return; //Если форма закрыта принудительно, то data будет иметь значение null
                        //new PlayerConfigs().writeConfig(event.getPlayer().getName(), targetForm.getElements().toString());
                        if (new GetWeather().isValid(data.toString().replace("[", "").replace("]", ""))) {
                            new PlayerConfigs3().createPlayer(p.getName(), data.toString().replace("[", "").replace("]", ""));
                            targetPlayer.sendMessage(new Language().get("liveweather.commands.citysetter.success").replace("[Name]", data.toString().replace("[", "").replace("]", "")));
                        } else {
                            targetPlayer.sendMessage(new Language().get("liveweather.commands.citysetter.notvalid"));
                        }
                        //InstanceManager.getServer().getLogger().info(targetForm.getElements().toString());
                    });
                } else {
                    p.sendMessage(new Language().get("liveweather.commands.citysetter.already"));
                }
            }
        }else{
            commandSender.sendMessage(new Language().get("liveweather.commands.server"));
        }
        return false;
    }
}
