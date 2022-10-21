package com.liveweather.formapi.forms.elements;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.window.FormWindowCustom;
import com.liveweather.formapi.forms.handlers.ClickedButton;
import com.liveweather.formapi.forms.CustomFormResponse;
import com.liveweather.formapi.forms.Form;
import com.liveweather.formapi.forms.custom.Button;

public class LoginForm extends Form {
    private boolean hasEmail = false;
    private ClickedButton clicked = null;
    public Button okbutton = null;
    public Button cancelbutton = null;
    public ElementInput password = null;
    public ElementInput email = null;
    public LoginForm(String title) {
        this.form = new FormWindowCustom(title);
        okbutton = new Button("");
        cancelbutton = new Button("");
        password = new ElementInput("");
        email = new ElementInput("");
    }
    public void setHasEmail() {
        hasEmail = true;
    }
    public void setOkText(String text) {
        okbutton.setText(text);
    }
    public void setCancelText(String text) {
        cancelbutton.setText(text);
    }
    public void setHandler(ClickedButton button) {
        clicked = button;
    }
    public void open(Player p) {
        send(p, (targetPlayer, targetForm, data) -> {
            if(data == null) {
                clicked.onCancel();
            }else{
                clicked.onOk();
            }
        });
    }
    private void send(Player player, CustomFormResponse response){
        if(hasEmail) {
            ((FormWindowCustom) form).addElement(email);
        }
        ((FormWindowCustom) form).addElement(password);
        ((FormWindowCustom) form).addElement(okbutton);
        ((FormWindowCustom) form).addElement(cancelbutton);
        playersForm.put(player.getName(), (FormResponse) response);
        player.showFormWindow(form);
    }
}
