package com.exemple.kulynych.model;

import java.awt.Point;

public class Ball {
    private World world;

    // free fall acceleration, in pixels/s**2. TODO: experiment.
    private final Point G = new Point(0, 400);
    Point coordinates = new Point(100, 100);

    // in pixels/s
    Point speed = new Point(100, 0);
    private int radius;
    private long timestamp = 0;

    public Ball(World world, int radius) {
        this.world = world;
        this.radius = radius;
    }

    // Calculate the new Ball's coordinates and speed at the given moment "timestamp"
    public void tick(long timestamp) {
        if (this.timestamp == 0) {
            this.timestamp = timestamp;
            return;
        }

        // gravity
        long dt = timestamp - this.timestamp;
        int dsy = (int) (G.y * dt / 1000);
        this.speed.y = this.speed.y + dsy;

        if (this.coordinates.y >= world.getHeight() - radius * 2) {
            this.speed.y = -(Math.abs(this.speed.y) * 95 / 100);
        }
        if (this.coordinates.y <= 0) {
            this.speed.y = Math.abs(this.speed.y) * 95 / 100;
        }
        if (this.coordinates.x >= world.getWidth() - radius * 9 / 7) {
            this.speed.x = -(Math.abs(this.speed.x) * 95 / 100);
        }
        if (this.coordinates.x <= 0) {
            this.speed.x = Math.abs(this.speed.x) * 95 / 100;
        }


        // coordinates
        this.coordinates.x = (int) (this.coordinates.x + this.speed.x * dt / 1000);
        this.coordinates.y = (int) (this.coordinates.y + this.speed.y * dt / 1000);

        this.timestamp = timestamp;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "coordinates=" + coordinates +
                ", speed=" + speed +
                '}';
    }

    public int getRadius() {
        return radius;
    }
}
