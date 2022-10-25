package com.liveweather.Simulator.forms;

import cn.nukkit.Player;
import com.liveweather.formapi.forms.ModalFormResponse;

import javax.swing.*;
import java.awt.*;

public class ModalForm extends com.liveweather.formapi.forms.elements.ModalForm {
    JFrame f = new JFrame();
    JPanel controls = new JPanel();
    JButton ok = new JButton();
    JButton cancel = new JButton();
    JTextArea area = new JTextArea();
    public ModalForm(String title, String content, String truebutton, String falsebuton) {
        f.setTitle(title);
        area.setText(content);
        area.setEditable(false);
        ok.setText(truebutton);
        cancel.setText(falsebuton);
        controls.add(ok);
        controls.add(cancel);
        f.add(area, BorderLayout.CENTER);
        f.add(controls, BorderLayout.SOUTH);
    }

    @Override
    public void send(Player player, ModalFormResponse response) {
        f.setVisible(true);
        f.pack();
    }
}
