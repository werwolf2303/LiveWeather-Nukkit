package com.liveweather.formapi.forms.custom;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;

public class Button extends Element {
    private String text = "";
    private ElementButtonImageData image;

    public Button(String text) {
        this.text = text;
    }

    public Button(String text, ElementButtonImageData image) {
        this.text = text;
        if (!image.getData().isEmpty() && !image.getType().isEmpty()) {
            this.image = image;
        }

    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ElementButtonImageData getImage() {
        return this.image;
    }

    public void addImage(ElementButtonImageData image) {
        if (!image.getData().isEmpty() && !image.getType().isEmpty()) {
            this.image = image;
        }

    }
}
