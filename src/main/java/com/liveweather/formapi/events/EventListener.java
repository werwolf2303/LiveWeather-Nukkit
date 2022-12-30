package com.liveweather.formapi.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;
import com.liveweather.formapi.forms.CustomFormResponse;
import com.liveweather.formapi.forms.Form;
import com.liveweather.formapi.forms.ModalFormResponse;
import com.liveweather.formapi.forms.SimpleFormResponse;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings (value="unchecked")
public class EventListener implements Listener {

    @SuppressWarnings (value="unused")
    @EventHandler(priority = EventPriority.HIGH)
    public void formResponded(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        FormWindow window = event.getWindow();
        FormResponse response = window.getResponse();

        if (Form.playersForm.containsKey(player.getName())) {
            com.liveweather.formapi.forms.FormResponse temp = Form.playersForm.get(player.getName());
            Form.playersForm.remove(player.getName());

            Object data;

            if (response == null || event.wasClosed()) {
                if(temp instanceof CustomFormResponse){
                    ((CustomFormResponse) temp).handle(player, (FormWindowCustom) window, null);

                }else if(temp instanceof ModalFormResponse) {
                    ((ModalFormResponse) temp).handle(player, (FormWindowModal) window, -1);

                }else if(temp instanceof SimpleFormResponse){
                    ((SimpleFormResponse) temp).handle(player, (FormWindowSimple) window, -1);
                }
                return;
            }

            if (window instanceof FormWindowSimple) {
                data = ((FormResponseSimple) response).getClickedButtonId();
                ((SimpleFormResponse) temp).handle(player, (FormWindowSimple) window, (int) data);
                return;
            }
            if (window instanceof FormWindowCustom) {
                data = new ArrayList<>(((FormResponseCustom) response).getResponses().values());
                ((CustomFormResponse) temp).handle(player, (FormWindowCustom) window, (List<Object>) data);
                return;
            }

            if (window instanceof FormWindowModal) {
                data = ((FormResponseModal) response).getClickedButtonId();
                ((ModalFormResponse) temp).handle(player, (FormWindowModal) window, (int) data);
            }
        }
    }

    @SuppressWarnings(value="unused")
    @EventHandler(priority = EventPriority.HIGH)
    public void playerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Form.playersForm.remove(player.getName());
    }
}