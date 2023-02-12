package com.liveweather.debug;

import com.liveweather.Logger;
import com.windowsplus.Elements;
import com.windowsplus.TextElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Font.BOLD;

@SuppressWarnings("unused")
public class DebugLogger implements Logger {
    final JFrame debugFrame;
    final JScrollPane pane;
    final GraphicsManager mholder;
    String title = "";
    final ArrayList<Elements> texts = new ArrayList<>();
    int my = 22;
    class GraphicsManager extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            debugFrame.setTitle(title);
            g.setFont(new Font(Font.DIALOG, BOLD, 13));
            for(Elements e : texts) {
                e.draw(g);
            }
        }
    }
    public DebugLogger() {
        debugFrame = new JFrame();
        mholder = new GraphicsManager();
        pane = new JScrollPane(mholder);
        debugFrame.setMinimumSize(new Dimension(600, 800));
    }

    @Override
    public void debugging(String message) {
        createTextModule("[LiveWeather::Debugging] " + message, Color.BLUE);
    }

    @Override
    public void throwable(Throwable throwable) {
        createTextModule("[LiveWeather::Throwable] " + throwable.getMessage(), Color.magenta);
    }

    @Override
    public void fatal(String message) {
        createTextModule("[LiveWeather::FATAL] " + message, Color.PINK);
    }

    @Override
    public void critical(String message) {
        createTextModule("[LiveWeather::Critical] " + message, Color.RED);
    }

    @Override
    public void extension(String message) {
        createTextModule("[LiveWeather <Extension>] " + message, Color.CYAN);
    }

    @Override
    public void warning(String message) {
        createTextModule("[LiveWeather::Warning] " + message, Color.YELLOW);
    }

    @Override
    public void normal(String message) {
        createTextModule("[LiveWeather] " + message, Color.BLACK);
    }

    @Override
    public void error(String message) {
        createTextModule("[LiveWeather::Error] " + message, Color.ORANGE);
    }

    public void custom(String message) {
        createTextModule(message, Color.BLACK);
    }

    void createTextModule(String text, Color c) {
        TextElement element = new TextElement(text, c);
        element.setY(my);
        element.setX(10);
        my+=22;
        if(my>debugFrame.getHeight()) {
            debugFrame.setTitle(title + " - Clearing Console");
            my=22;
            texts.clear();
        }
        texts.add(element);
        mholder.repaint();
    }

    public void create() {
        debugFrame.setTitle("DebugWindow");
        title = debugFrame.getTitle();
        debugFrame.add(pane, BorderLayout.CENTER);
        debugFrame.setVisible(true);
        debugFrame.pack();
    }
}
