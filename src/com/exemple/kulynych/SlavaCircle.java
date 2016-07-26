package com.exemple.kulynych;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class SlavaCircle extends JComponent{


    public void paint(Graphics g) {
        Graphics2D g2;
        int height = getHeight();
        int width = getWidth();
        g2 = (Graphics2D) g;
        Ellipse2D.Double ellipse
                = new Ellipse2D.Double(0, 0, width, height);
        System.out.print(g2);
        g2.fill(ellipse);
    }
}
