package com.liveweather.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import com.liveweather.PublicValues;
import com.liveweather.config.ConfigValues;
import com.liveweather.utils.FormUtils;

public class PlayerEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!PublicValues.playerStorageProvider.hasEntered(e.getPlayer().getUniqueId())) {
            if(PublicValues.config.getBoolean(ConfigValues.autoFind.name)) {
                FormUtils.openTrackingAgreementForm(e.getPlayer());
            }else {
                FormUtils.openLocationSelectForm(e.getPlayer());
            }
            return;
        }
        if(!PublicValues.playerStorageProvider.didAccept(e.getPlayer().getUniqueId())) {
            // Player didn't put anything in (Reason: Didn't agree the agreement)
            return;
        }
    }
}
