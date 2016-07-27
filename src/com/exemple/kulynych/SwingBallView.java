package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class SwingBallView extends JComponent {

    private World world;

    public SwingBallView(World world) {
        this.world = world;
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Color colorStart = new Color(255, 0, 0, 255);
        Color colorFinish = new Color(255, 0, 0, 50);
        Graphics2D g2;
        int height = getHeight();
        g2 = (Graphics2D) g;

        Ellipse2D.Double ellipse = new Ellipse2D.Double(
                (double) world.getCoordinates().x,
                (double) world.getCoordinates().y,
                world.ball.getRadius(),
                world.ball.getRadius());
        Paint redToWhite = new GradientPaint(
                (float) world.getCoordinates().x, 0, colorStart,
                (float) world.getCoordinates().x + world.ball.getRadius() / 3 * 2, 0, colorFinish);

        g2.setPaint(redToWhite);
        g2.setStroke(new BasicStroke(5f));
        g2.fill(ellipse);
    }
}
