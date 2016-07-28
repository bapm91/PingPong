package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;

class SwingBallView extends JComponent {

    private Ball ball;
    private Ellipse2D.Double ellipse;
    private Ellipse2D.Double ellipseLine;
    private Paint redToWhite;

    public SwingBallView(Ball ball) {
        this.ball = ball;
    }

    private void paintBall(Ball ball) {

        Color colorStart = new Color(255, 0, 0, 255);
        Color colorFinish = new Color(255, 0, 0, 50);
        ellipse = new Ellipse2D.Double(
                (double) ball.getCoordinates().x,
                (double) ball.getCoordinates().y,
                ball.getDiametr(),
                ball.getDiametr());

        ellipseLine = new Ellipse2D.Double(
                (double) ball.getCoordinates().x,
                (double) ball.getCoordinates().y,
                ball.getDiametr(),
                ball.getDiametr());

        redToWhite = new GradientPaint(
                (float) ball.getCoordinates().x, 0, colorStart,
                (float) ball.getCoordinates().x + ball.getDiametr() / 3 * 2, 0, colorFinish);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);

        Color colorBlack = new Color(0, 0, 0, 255);
        Graphics2D g2;
        int height = getHeight();
        g2 = (Graphics2D) g;

//        world.balls.forEach(this::paintBall);
        this.paintBall(ball);

        g2.setPaint(redToWhite);
        g2.setStroke(new BasicStroke(5f));
        g2.fill(ellipse);
        g2.setStroke(new BasicStroke(1f));
        g2.setPaint(colorBlack);
        g2.draw(ellipseLine);
    }
}
