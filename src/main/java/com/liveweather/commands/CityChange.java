package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.PublicValues;

public class CityChange extends Command {
    public CityChange(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player player = (Player) commandSender;
            if(PublicValues.playerStorageProvider.hasEntered(player.getUniqueId())) {
                /*CustomForm form = new CustomForm()
                        .addInput(PublicValues.language.translate("liveweather.forms.button"));
                form.setTitle(PublicValues.language.translate("liveweather.forms.title"));
                form.send(player, (targetPlayer, targetForm, data) -> {
                    if(data == null) return; //Если форма закрыта принудительно, то data будет иметь значение null
                    try {
                        if(!PublicValues.weatherDataProvider.isCityValid(data.toString().replace("[", "").replace("]", ""))) {
                            //PublicValues.playerStorageProvider.changePlayerCity(player.getUniqueId(), data.toString().replace("[", "").replace("]", ""));
                            targetPlayer.sendMessage(PublicValues.language.translate("liveweather.commands.citychange.successfull") + data.toString().replace("[", "").replace("]", ""));
                        }else{
                            targetPlayer.sendMessage(PublicValues.language.translate("liveweather.commands.citysetter.notvalid"));
                        }
                    } catch (IOException e) {
                        targetPlayer.sendMessage(PublicValues.language.translate("liveweather.commands.citysetter.internalerror"));
                    }
                });*/
            }else{
                player.sendMessage(PublicValues.language.translate("liveweather.commands.citychange.dontentered"));
            }
        }else{
            commandSender.sendMessage(PublicValues.language.translate("liveweather.commands.server"));
        }
        return false;
    }
}
