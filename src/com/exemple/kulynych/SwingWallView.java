package com.exemple.kulynych;

import com.exemple.kulynych.model.Wall;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class SwingWallView extends JComponent {

    private World world;
    private final Color colorBlack = new Color(0, 0, 0, 255);

    public SwingWallView(World world) {
        this.world = world;
    }

    private void paintBall(Graphics2D g2, Wall wall) {

        g2.draw(new Rectangle2D.Double(
                wall.getWallPosition().x,
                wall.getWallPosition().y,
                wall.getRectWidth(),
                wall.getRectHeight()));
        g2.setPaint(colorBlack);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);

        for (Wall wall : world.wall) {
            paintBall((Graphics2D) g, wall);
        }
    }
}
