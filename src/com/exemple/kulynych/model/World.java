package com.exemple.kulynych.model;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class World extends Component {
    public List<Ball> balls = new LinkedList<>();
    public List<Wall> wall = new LinkedList<>();
    private int width;
    private int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void tick(long time) {
        for(Ball ball: balls) {
            ball.tick(time);
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
