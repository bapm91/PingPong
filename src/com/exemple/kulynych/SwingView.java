package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.Wall;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SwingView extends JComponent {
    private World world;

    private final Color colorBlack = new Color(0, 0, 0, 255);
    private Color colorStart = new Color(255, 0, 0, 255);
    private Color colorFinish = new Color(255, 0, 0, 50);

    public SwingView(World world) {
        this.world = world;
    }

    private void paintBall(Graphics2D g2, Ball ball) {

        double r = ball.getDiameter() / 2;

        Ellipse2D.Double ellipse = new Ellipse2D.Double(
                (double) ball.getCoordinates().x - r,
                (double) ball.getCoordinates().y - r,
                ball.getDiameter(),
                ball.getDiameter());

        Ellipse2D.Double ellipseLine = new Ellipse2D.Double(
                (double) ball.getCoordinates().x - r,
                (double) ball.getCoordinates().y - r,
                ball.getDiameter(),
                ball.getDiameter());

        Paint redToWhite = new GradientPaint(
                (float) ball.getCoordinates().x, 0, colorStart,
                (float) ball.getCoordinates().x + ball.getDiameter() / 3 * 2, 0, colorFinish);

        g2.setPaint(redToWhite);
        g2.setStroke(new BasicStroke(5f));
        g2.fill(ellipse);
        g2.setStroke(new BasicStroke(1f));
        g2.setPaint(colorBlack);
        g2.draw(ellipseLine);
    }

    public void paint(Graphics g) {

        for (Ball ball : world.balls) {
            paintBall((Graphics2D) g, ball);
        }
        for (Wall wall : world.walls) {
            paintWall((Graphics2D) g, wall);
        }
    }

    private void paintWall(Graphics2D g2, Wall wall) {

        g2.draw(new Rectangle2D.Double(
                wall.getWallPosition().x - wall.getRectWidth() / 2,
                wall.getWallPosition().y - wall.getRectHeight() / 2,
                wall.getRectWidth(),
                wall.getRectHeight()));
        g2.setPaint(colorBlack);
    }
}
