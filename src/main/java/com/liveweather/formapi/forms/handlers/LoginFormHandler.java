package com.liveweather.formapi.forms.handlers;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;

public interface LoginFormHandler {

    void onOk(String email, String password, Player p);
    void onOk(String password, Player p);
    void onCancel();
}