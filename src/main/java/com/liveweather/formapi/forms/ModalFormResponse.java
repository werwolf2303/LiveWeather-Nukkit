package com.liveweather.formapi.forms;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowModal;

public interface ModalFormResponse extends FormResponse {

    void handle(Player targetPlayer, FormWindowModal targetForm, int data);

}
