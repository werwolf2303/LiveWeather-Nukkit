package com.liveweather.Simulator;

import com.liveweather.instances.InstanceManager;

import javax.swing.*;
import java.awt.*;

public class DebugFrame {
    public static class Content extends JPanel {
        private JTextArea Debug;

        public Content() {
            //construct components
            Debug = new JTextArea (5, 5);

            //adjust size and set layout
            setPreferredSize (new Dimension(1182, 701));
            setLayout (null);

            //add components
            add (Debug);
            Debug.setEditable(false);

            //set component bounds (only needed by Absolute Positioning)
            Debug.setBounds (0, 0, 1180, 700);
            InstanceManager.setDebugFrame(this);
        }
        public void add(String text) {
            if(Debug.getText().equals("")) {
                Debug.setText(text);
            }else{
                Debug.setText(Debug.getText()+"\n"+text);
            }
        }
    }
    public void init() {
        JFrame frame = new JFrame("Debug Window");
        GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = localGraphicsEnvironment.getScreenDevices();
        if(localGraphicsEnvironment.getScreenDevices().length>1) {
            //frame.setLocation(gd[2].getDefaultConfiguration().getBounds().x, frame.getY()/2);
        }
        frame.getContentPane().add(new Content());
        frame.setVisible(true);
        frame.pack();
    }
}
