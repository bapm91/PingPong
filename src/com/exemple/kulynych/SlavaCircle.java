package com.exemple.kulynych;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class SlavaCircle extends JComponent {

    public void paint(Graphics g) {

        Color colorStart = new Color(255, 0, 0, 255);
        Color colorFinish = new Color(255, 0, 0, 50);
        Graphics2D g2;
        int height = getHeight();
        int width = getWidth();
        g2 = (Graphics2D) g;
        Ellipse2D.Double ellipse
                = new Ellipse2D.Double(width / 2, 50, height / 7, height / 7);
        System.out.print(g2);
        Paint redToWhite = new GradientPaint(width / 2, 0, colorStart, width / 2 + 40, 0, colorFinish);
        g2.setPaint(redToWhite);

        g2.setStroke(new BasicStroke(5f));
        g2.draw(ellipse);
    }
}
