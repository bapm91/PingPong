package com.exemple.kulynych;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class SwingBallView extends JComponent {

    private Ball ball;

    public SwingBallView(Ball ball) {
        this.ball = ball;
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Color colorStart = new Color(255, 0, 0, 255);
        Color colorFinish = new Color(255, 0, 0, 50);
        Graphics2D g2;
        int height = getHeight();
        g2 = (Graphics2D) g;

        Ellipse2D.Double ellipse = new Ellipse2D.Double(
                (double) ball.getCoordinates().x,
                (double) ball.getCoordinates().y,
                height / 7,
                height / 7);
        Paint redToWhite = new GradientPaint((float) ball.getCoordinates().x, 0, colorStart, (float) ball.getCoordinates().x + 40, 0, colorFinish);

        g2.setPaint(redToWhite);
        g2.setStroke(new BasicStroke(5f));
        g2.draw(ellipse);
    }
}
