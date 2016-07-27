package com.exemple.kulynych;

import java.awt.Point;

class Ball {
    // free fall acceleration, in pixels/s**2. TODO: experiment.
    private final Point G = new Point(0, 400);

    private Point coordinates = new Point(100, 100);

    // in pixels/s
    private Point speed = new Point(0, 0);
    private long timestamp = 0;

    // Calculate the new Ball's coordinates and speed at the given moment "timestamp"
    public void tick(long timestamp) {
        if (this.timestamp == 0) {
            this.timestamp = timestamp;
            return;
        }

        // gravity
        long dt = timestamp - this.timestamp;
        int dsy = (int) (G.y * dt / 1000);
        System.out.println("Dsy = " + dsy);
        this.speed.y = this.speed.y + dsy;

        if (this.coordinates.y >= 400) {
            this.speed.y = -this.speed.y;
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
}