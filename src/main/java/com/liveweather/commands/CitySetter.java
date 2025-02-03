package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.PublicValues;

import java.io.IOException;

public class CitySetter extends Command {
    public CitySetter(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender.isPlayer()) {
            Player player = (Player) commandSender;
            if(strings.length>0) {
                try {
                    if (PublicValues.weatherDataProvider.isCityValid(strings[0].toString().replace("[", "").replace("]", ""))) {
                        //PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), strings[0].toString().replace("[", "").replace("]", ""));
                        player.sendMessage(PublicValues.language.translate("liveweather.commands.citysetter.success").replace("[Name]", strings[0].toString().replace("[", "").replace("]", "")));
                    } else {
                        player.sendMessage(PublicValues.language.translate("liveweather.commands.citysetter.notvalid"));
                    }
                } catch (IOException e) {
                    player.sendMessage(PublicValues.language.translate("general.internalerror"));
                }
            }else {
                if (!PublicValues.playerStorageProvider.hasEntered(player.getUniqueId())) {
                    /*CustomForm form = new CustomForm()
                            .addInput(PublicValues.language.translate("liveweather.forms.button"));
                    form.setTitle(PublicValues.language.translate("liveweather.forms.title"));
                    form.send(player, (targetPlayer, targetForm, data) -> {
                        if (data == null) return; //Если форма закрыта принудительно, то data будет иметь значение null
                        //new PlayerConfigs().writeConfig(event.getPlayer().getName(), targetForm.getElements().toString());
                        try {
                            if (PublicValues.weatherDataProvider.isCityValid(data.toString().replace("[", "").replace("]", ""))) {
                               // PublicValues.playerStorageProvider.createPlayer(player.getUniqueId(), data.toString().replace("[", "").replace("]", ""));
                                targetPlayer.sendMessage(PublicValues.language.translate("liveweather.commands.citysetter.success").replace("[Name]", data.toString().replace("[", "").replace("]", "")));
                            } else {
                                targetPlayer.sendMessage(PublicValues.language.translate("liveweather.commands.citysetter.notvalid"));
                            }
                        } catch (IOException e) {
                            LWLogging.throwable(e);
                            targetPlayer.sendMessage(PublicValues.language.translate("general.internalerror"));
                        }
                    });*/
                } else {
                    player.sendMessage(PublicValues.language.translate("liveweather.commands.citysetter.already"));
                }
            }
        }else{
            commandSender.sendMessage(PublicValues.language.translate("liveweather.commands.server"));
        }
        return false;
    }
}
