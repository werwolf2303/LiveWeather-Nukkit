package com.liveweather.test;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.BlockGlassStained;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.event.level.WeatherEvent;
import cn.nukkit.event.server.DataPacketSendEvent;
import cn.nukkit.level.Level;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.LevelEventPacket;
import cn.nukkit.potion.Effect;
import cn.nukkit.potion.Potion;
import com.liveweather.api.SetWeather;
import com.liveweather.commandline.LWLogging;
import com.liveweather.extensions.ExtensionLoader;
import com.liveweather.language.Language;
import com.liveweather.language.LanguageV2;
import com.liveweather.storage.PlayerConfigs;
import com.liveweather.storage.PlayerConfigs2;
import jdk.nashorn.internal.ir.Block;
import ru.nukkitx.forms.elements.CustomForm;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class TestCommand extends Command {
    public TestCommand(String name, String description) {
        super(name, description);
    }
    Boolean raining = false;
    String playername = "";
    int raintime = 0;
    public void setRainTime(int rainTime) {
        raintime = rainTime;
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            new SetWeather().setRaining(p);
        }
        return false;
    }
}
