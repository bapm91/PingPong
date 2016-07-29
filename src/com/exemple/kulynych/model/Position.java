package com.exemple.kulynych.model;

import java.awt.*;

public class Position {

    public Point position(World world, int width, int height) {
        int windowHeight = world.getHeight();
        int windowWidth = world.getWidth();
        int coordinatesX = (int) (Math.random() * windowWidth);
        int coordinatesY = (int) (Math.random() * windowHeight);

        if (coordinatesX + width >= world.getWidth()){
            coordinatesX -= width;
        }
        if (coordinatesY + height >= world.getHeight()){
            coordinatesY -= height;
        }

        return new Point(coordinatesX, coordinatesY);
    }
}