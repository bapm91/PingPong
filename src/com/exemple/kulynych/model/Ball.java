package com.exemple.kulynych.model;

import java.awt.*;

public class Ball {
    private World world;

    // free fall acceleration, in pixels/s**2. TODO: experiment.
    private final Point G = new Point(0, 400);
    private int diametr;
    private int minSpeed = 100;
    private long timestamp = 0;

    public Point coordinates;
    private Point center;

    public Ball(World world, int diametr) {
        this.world = world;
        this.diametr = diametr;
        coordinates();
        center();
    }

    private void coordinates() {
        Position position = new Position();
        coordinates = position.position(world, diametr, diametr);
    }

    private void center() {
        center = new Point(coordinates.x + diametr / 2, coordinates.y + diametr / 2);
    }

    // in pixels/s
    Point speed = new Point(
            minSpeed + (int) (Math.random() * 200),
            minSpeed + (int) (Math.random() * 200));

    public void calculateNewVelocities(Ball ballSecond) {
        int mass1 = this.getDiametr() / 2;
        int mass2 = ballSecond.getDiametr() / 2;
        int velX1 = this.getSpeed().x;
        int velX2 = ballSecond.getSpeed().x;
        int velY1 = this.getSpeed().y;
        int velY2 = ballSecond.getSpeed().y;

        int newVelX1 = (velX1 * (mass1 - mass2) + (2 * mass2 * velX2)) / (mass1 + mass2);
        int newVelX2 = (velX2 * (mass2 - mass1) + (2 * mass1 * velX1)) / (mass1 + mass2);
        int newVelY1 = (velY1 * (mass1 - mass2) + (2 * mass2 * velY2)) / (mass1 + mass2);
        int newVelY2 = (velY2 * (mass2 - mass1) + (2 * mass1 * velY1)) / (mass1 + mass2);

        this.setSpeed(newVelX1, newVelY1);
        ballSecond.setSpeed(newVelX2, newVelY2);
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

        if (this.coordinates.y >= world.getHeight() - diametr / 2) {
            this.speed.y = -Math.abs(this.speed.y) * 95 / 100;
        }
        if (this.coordinates.y <= diametr / 2) {
            this.speed.y = Math.abs(this.speed.y) * 95 / 100;
        }
        if (this.coordinates.x >= world.getWidth() - diametr / 2) {
            this.speed.x = -Math.abs(this.speed.x) * 95 / 100;
        }
        if (this.coordinates.x <= diametr / 2) {
            this.speed.x = Math.abs(this.speed.x) * 95 / 100;
        }

        if (this.coordinates.x >= world.getWidth() + diametr * 2) {
            this.coordinates.x = Position.rePositionX(world);
        }
        if (this.coordinates.y >= world.getHeight() + diametr * 2) {
            this.coordinates.y = Position.rePositionY(world);
        }

        // coordinates
        this.coordinates.x = (int) (this.coordinates.x + this.speed.x * dt / 1000);
        this.coordinates.y = (int) (this.coordinates.y + this.speed.y * dt / 1000);

        this.timestamp = timestamp;
        center();
    }

    public void detectWallCollision(Wall wall) {
        if (wall.contains(this)) {
            if (wall.relatively(this).equals("Right")) {
                this.speed.x = Math.abs(this.speed.x);
            }
            if (wall.relatively(this).equals("Left")) {
                this.speed.x = -Math.abs(this.speed.x);
            }
            if (wall.relatively(this).equals("Top")) {
                this.speed.y = -Math.abs(this.speed.y);
            }
            if (wall.relatively(this).equals("Bottom")) {
                this.speed.y = Math.abs(this.speed.y);
            }
        }

    }

    public void detectBallCollision(Ball other) {
        if (other == this) {
            return;
        }

        int radius = diametr / 2;
        int radiusSecondBall = other.getDiametr() / 2;

        if (coordinates.x + radius + radiusSecondBall > other.coordinates.x
                && coordinates.x < other.coordinates.x + radius + radiusSecondBall
                && coordinates.y + radius + radiusSecondBall > other.coordinates.y
                && coordinates.y < other.coordinates.y + radius + radiusSecondBall) {
            if (this.distanceTo(other) < radius + radiusSecondBall) {
                calculateNewVelocities(other);
            }
        }
    }

    public Double distanceTo(Ball ballSecond) {
        double distance;
        distance = Math.sqrt(
                (this.coordinates.x - ballSecond.coordinates.x)
                        * (this.coordinates.x - ballSecond.coordinates.x)
                        + (this.coordinates.y - ballSecond.coordinates.y)
                        * (this.coordinates.y - ballSecond.coordinates.y));
        return distance;
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

    public int getDiametr() {
        return diametr;
    }

    public Point getSpeed() {
        return speed;
    }

    public void setSpeed(int x, int y) {
        this.speed.x = x;
        this.speed.y = y;
    }

    public Point getCenter() {
        return center;
    }
}
