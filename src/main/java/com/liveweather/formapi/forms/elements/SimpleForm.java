package com.liveweather.formapi.forms.elements;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import com.liveweather.formapi.forms.Form;
import com.liveweather.formapi.forms.SimpleFormResponse;

@SuppressWarnings("unused")
public class SimpleForm extends Form {

    public SimpleForm(FormWindowSimple form){
        this.form = form;
    }

    public SimpleForm() {
        form = new FormWindowSimple("", "");
    }

    public SimpleForm(String title) {
        form = new FormWindowSimple(title, "");
    }

    public SimpleForm(String title, String content) {
        form = new FormWindowSimple(title, content);
    }

    public void send(Player player, SimpleFormResponse response) {
        playersForm.put(player.getName(), response);
        player.showFormWindow(form);
    }

    public SimpleForm setTitle(String value) {
        ((FormWindowSimple) form).setTitle(value);
        return this;
    }

    public SimpleForm setContent(String value) {
        ((FormWindowSimple) form).setContent(value);
        return this;
    }

    public SimpleForm addContent(String value){
        ((FormWindowSimple) form).setContent(((FormWindowSimple) form).getContent() + value);
        return this;
    }

    public SimpleForm addContentLine(String value){
        return addContent(value + "\n");
    }

    public SimpleForm addContentOnNextLine(String value){
        return addContent("\n" + value);
    }

    public SimpleForm addButton(String text) {
        ((FormWindowSimple) form).addButton(new ElementButton(text));
        return this;
    }

    public SimpleForm addButton(String text, ImageType type, String ico) {
        ElementButton button = new ElementButton(text);
        button.addImage(new ElementButtonImageData((type == ImageType.PATH) ? "path" : "url", ico));
        ((FormWindowSimple) form).addButton(button);
        return this;
    }
}