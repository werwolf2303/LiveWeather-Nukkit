package com.liveweather.formapi.forms.custom;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import com.liveweather.formapi.forms.CustomFormResponse;
import com.liveweather.formapi.forms.Form;
import com.liveweather.formapi.forms.handlers.LoginFormHandler;

public class LoginForm extends Form {
    private boolean hasEmail = false;
    private ElementLabel eml = null;
    private ElementLabel pml = null;
    private boolean hasEml = false;
    private boolean hasPml = false;
    private ElementInput email = null;
    private ElementInput password = null;
    private LoginFormHandler handler = null;

    public LoginForm(String title) {
        this.form = new FormWindowCustom(title);
        email = new ElementInput("");
        password = new ElementInput("");
    }
    public void setHasEmail() {
        hasEmail = true;
    }
    public void setHandler(LoginFormHandler lfh) {
        handler = lfh;
    }

    public ElementLabel getEmailLabel() {
        return eml;
    }

    public ElementLabel getPasswordLabel() {
        return pml;
    }

    public ElementInput getEmailInput() {
        return email;
    }

    public ElementInput getPasswordInput() {
        return password;
    }

    public void setEmailPlaceholder(String text) {
        email.setPlaceHolder(text);
    }

    public void setPasswordPlaceholder(String text) {
        password.setPlaceHolder(text);
    }

    public void addLabelEmail(String text) {
        hasEml = true;
        pml = new ElementLabel(text);
    }

    public void addLabelPassword(String text) {
        hasPml = true;
        eml = new ElementLabel(text);
    }

    private void send(Player player, CustomFormResponse response){
        playersForm.put(player.getName(), response);
        player.showFormWindow(form);
    }
    public void open(Player p) {
        if(hasEmail) {
            if(hasEml) {
                ((FormWindowCustom) form).addElement(eml);
            }
            ((FormWindowCustom) form).addElement(email);
        }
        if(hasPml) {
            ((FormWindowCustom) form).addElement(pml);
        }
        ((FormWindowCustom) form).addElement(password);
        send(p, (targetPlayer, targetForm, data) -> {
            if(data == null) {
                handler.onCancel();
            }else{
                String e = "";
                String pass = "";
                if(hasEmail) {
                    e = data.toString().replace("[", "").replace("]", "").split(",")[0].replaceFirst(" ", "");
                    pass = data.toString().replace("[", "").replace("]", "").split(",")[1].replaceFirst(" ", "");
                }else{
                    pass = data.toString().replace("[", "").replace("]", "").split(",")[1].replaceFirst(" ", "");
                }
                if(hasEmail) {
                    handler.onOk(e, pass, p);
                }else{
                    handler.onOk(pass, p);
                }

            }
        });
    }
}
