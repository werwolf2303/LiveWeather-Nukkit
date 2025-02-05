package com.liveweather.forms;

import cn.nukkit.Player;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowCustom;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Form {
    private final ArrayList<Element> elements;
    private String windowTitle;
    private Object windowIcon;
    private com.liveweather.forms.FormResponseHandler onFormResponse;
    private Runnable onClose;

    public Form(String windowTitle) {
        if(windowTitle == null) throw new UnsupportedOperationException("Window title cannot be null");
        this.windowTitle = windowTitle;
        this.elements = new ArrayList<>();
    }

    public Form(String windowTitle, String iconURL) {
        if(windowTitle == null) throw new UnsupportedOperationException("Window title cannot be null");
        this.windowTitle = windowTitle;
        this.windowIcon = iconURL;
        this.elements = new ArrayList<>();
    }

    public Form(String windowTitle, ElementButtonImageData iconImageData) {
        if(windowTitle == null) throw new UnsupportedOperationException("Window title cannot be null");
        this.windowTitle = windowTitle;
        this.windowIcon = iconImageData;
        this.elements = new ArrayList<>();
    }

    public Form(String windowTitle, ArrayList<Element> elements) {
        if(windowTitle == null) throw new UnsupportedOperationException("Window title cannot be null");
        this.windowTitle = windowTitle;
        this.elements = elements;
    }

    public Form(String windowTitle, ArrayList<Element> elements, String iconURL) {
        if(windowTitle == null) throw new UnsupportedOperationException("Window title cannot be null");
        this.windowTitle = windowTitle;
        this.elements = elements;
        this.windowIcon = iconURL;
    }

    public Form(String windowTitle, ArrayList<Element> elements, ElementButtonImageData iconURL) {
        if(windowTitle == null) throw new UnsupportedOperationException("Window title cannot be null");
        this.windowTitle = windowTitle;
        this.elements = elements;
        this.windowIcon = iconURL;
    }

    protected void setOnFormResponse(com.liveweather.forms.FormResponseHandler onFormResponse) {
        this.onFormResponse = onFormResponse;
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void removeElement(Element element) {
        elements.remove(element);
    }

    public void setOnClose(Runnable onClose) {
        this.onClose = onClose;
    }

    public void removeAllElements() {
        elements.clear();
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public void setIconURL(String iconURL) {
        this.windowIcon = iconURL;
    }

    public void presentToPlayer(Player player) {
        FormWindowCustom internalFormWindow = new FormWindowCustom(windowTitle, elements);
        if(windowIcon != null) {
            if(windowIcon instanceof String) {
                internalFormWindow.setIcon((String)windowIcon);
            }
            if(windowIcon instanceof ElementButtonImageData) {
                internalFormWindow.setIcon((ElementButtonImageData)windowIcon);
            }
        }
        internalFormWindow.addHandler((player1, i) -> {
            if(internalFormWindow.getResponse() == null) {
                onClose.run();
                return;
            }
            onFormResponse.onResponse(internalFormWindow.getResponse(), i);
        });
        player.showFormWindow(internalFormWindow);
    }

    public void closeForm(Player player) {
        player.closeFormWindows();
    }
}
