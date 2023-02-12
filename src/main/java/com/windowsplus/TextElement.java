package com.windowsplus;

import java.awt.*;

@SuppressWarnings("unused")
public class TextElement implements Elements {
    int xaxis = 0;
    final Color color;
    int yaxis = 0;
    final String content;
    public TextElement(String text, Color c) {
        color = c;
        content = text;
    }

    @Override
    public void setX(int x) {
        xaxis = x;
    }

    @Override
    public void setY(int y) {
        yaxis = y;
    }

    @Override
    public int getX() {
        return xaxis;
    }

    @Override
    public int getY() {
        return yaxis;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawString(content, xaxis, yaxis);
        g.setColor(Color.BLACK);
    }
}
