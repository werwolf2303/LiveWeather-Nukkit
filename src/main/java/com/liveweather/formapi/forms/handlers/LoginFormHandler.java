package com.liveweather.formapi.forms.handlers;

import cn.nukkit.Player;

@SuppressWarnings({"EmptyMethod", "unused"})
public interface LoginFormHandler {

    void onOk(String email, String password, Player p);
    void onOk(String password, Player p);
    void onCancel();
}