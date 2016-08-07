package com.exemple.kulynych.model;

import java.awt.Point;

public class Position {

    public Point position(World world, int width, int height) {
        int windowHeight = world.getHeight();
        int windowWidth = world.getWidth();
        int coordinatesX = (int) (Math.random() * windowWidth);
        int coordinatesY = (int) (Math.random() * windowHeight);

        if (coordinatesX + width >= world.getWidth()) {
            coordinatesX -= width;
        }
        if (coordinatesY + height >= world.getHeight()) {
            coordinatesY -= height;
        }

        return new Point(coordinatesX, coordinatesY);
    }

    public static int rePositionX(World world) {
        return (int) (Math.random() * world.getWidth());
    }

    public static int rePositionY(World world) {
        return (int) (Math.random() * world.getHeight());
    }
}
