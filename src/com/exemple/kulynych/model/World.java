package com.exemple.kulynych.model;

import java.awt.*;

public class World extends Component {
    public Ball ball;
    private int width;
    private int height;
    private Platform platform;
    private long time;

    public World(long time) {
        this.time = time;
    }
    //    private int radius;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.ball = new Ball(this, 70);
    }

    public long getTime() {
        return time;
    }

    public Point getCoordinates() {
        return ball.getCoordinates();
    }

    public void tick(long l) {
        ball.tick(l);
    }
}
