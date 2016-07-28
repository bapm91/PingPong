package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;

class SwingBallView extends JComponent {

    private World world;

    private final Color colorBlack = new Color(0, 0, 0, 255);
    private Color colorStart = new Color(255, 0, 0, 255);
    private Color colorFinish = new Color(255, 0, 0, 50);

    public SwingBallView(World world) {
        this.world = world;
    }

    private void paintBall(Graphics2D g2, Ball ball) {

        double r = ball.getDiametr() / 2;

        Ellipse2D.Double ellipse = new Ellipse2D.Double(
                (double) ball.getCoordinates().x-r,
                (double) ball.getCoordinates().y-r,
                ball.getDiametr(),
                ball.getDiametr());

        Ellipse2D.Double ellipseLine = new Ellipse2D.Double(
                (double) ball.getCoordinates().x-r,
                (double) ball.getCoordinates().y-r,
                ball.getDiametr(),
                ball.getDiametr());

        Paint redToWhite = new GradientPaint(
                (float) ball.getCoordinates().x, 0, colorStart,
                (float) ball.getCoordinates().x + ball.getDiametr() / 3 * 2, 0, colorFinish);

        g2.setPaint(redToWhite);
        g2.setStroke(new BasicStroke(5f));
        g2.fill(ellipse);
        g2.setStroke(new BasicStroke(1f));
        g2.setPaint(colorBlack);
        g2.draw(ellipseLine);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);

        for (Ball ball: world.balls) {
            paintBall((Graphics2D) g, ball);
        }
    }
}
