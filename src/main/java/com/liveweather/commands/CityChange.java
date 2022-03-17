package com.liveweather.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.liveweather.storage.PlayerConfigs;
import ru.nukkitx.forms.elements.CustomForm;

public class CityChange extends Command {
    public CityChange(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(new PlayerConfigs().hasPlayerEnteredCityName(p.getName())) {
                CustomForm form = new CustomForm()
                        .addInput("Enter City");
                form.setTitle("Enter the City you live in");
                form.send(p, (targetPlayer, targetForm, data) -> {
                    if(data == null) return; //Если форма закрыта принудительно, то data будет иметь значение null
                    //new PlayerConfigs().writeConfig(event.getPlayer().getName(), targetForm.getElements().toString());
                    new PlayerConfigs().deleteConfig(p.getName());
                    new PlayerConfigs().writeConfig(p.getName(), data.toString().replace("[", "").replace("]", ""));
                    //Server.getInstance().getLogger().info(targetForm.getElements().toString());
                    targetPlayer.sendMessage("Successfull changed city to " + data.toString().replace("[", "").replace("]", ""));
                });
            }else{
                p.sendMessage("[LiveWeather] §cYou dont have entered your city, please use /setcity first");
            }
        }
        return false;
    }
}
