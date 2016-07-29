package com.exemple.kulynych.model;

import java.awt.*;

public class Wall {
    private World world;
    private Point wallPosition;
    private Point center;
    private int rectHeight;
    private int rectWidth;

    public Wall(World world, int rectWidth, int rectHeight) {
        this.world = world;
        this.rectHeight = rectHeight;
        this.rectWidth = rectWidth;
        Position position = new Position();
        wallPosition = position.position(world, rectWidth, rectHeight);
    }

    public Point getWallPosition() {
        return wallPosition;
    }

    public Point getCenter() {
        return center;
    }

    public int getRectHeight() {
        return rectHeight;
    }

    public int getRectWidth() {
        return rectWidth;
    }
}
