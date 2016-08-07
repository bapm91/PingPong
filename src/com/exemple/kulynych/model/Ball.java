package com.exemple.kulynych.model;

import java.awt.Point;

public class Ball {
    private World world;

    // free fall acceleration, in pixels/s**2. TODO: experiment.
    private final Point G = new Point(0, 400);
    private int diameter;
    private long timestamp = 0;

    private Point coordinates;
    private Point center;
    private Point speed;

    public Ball(World world, int diameter) {
        this.world = world;
        this.diameter = diameter;
        coordinates();
        center();
        speed();
    }

    private void coordinates() {
        Position position = new Position();
        coordinates = position.position(world, diameter, diameter);
    }

    private void center() {
        center = new Point(coordinates.x + diameter / 2, coordinates.y + diameter / 2);
    }

    // in pixels/s
    private void speed() {
        int minSpeed = 100;
        speed = new Point(
                minSpeed + (int) (Math.random() * 200),
                minSpeed + (int) (Math.random() * 200));
    }

    private void calculateNewVelocities(Ball ballSecond) {
        int mass1 = this.getDiameter() / 2;
        int mass2 = ballSecond.getDiameter() / 2;
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

        if (this.coordinates.y >= world.getHeight() - diameter / 2) {
            this.speed.y = -Math.abs(this.speed.y) * 95 / 100;
        }
        if (this.coordinates.y <= diameter / 2) {
            this.speed.y = Math.abs(this.speed.y) * 95 / 100;
        }
        if (this.coordinates.x >= world.getWidth() - diameter / 2) {
            this.speed.x = -Math.abs(this.speed.x) * 95 / 100;
        }
        if (this.coordinates.x <= diameter / 2) {
            this.speed.x = Math.abs(this.speed.x) * 95 / 100;
        }

        if (this.coordinates.x >= world.getWidth() + diameter * 2) {
            this.coordinates.x = Position.rePositionX(world);
        }
        if (this.coordinates.y >= world.getHeight() + diameter * 2) {
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

        int radius = diameter / 2;
        int radiusSecondBall = other.getDiameter() / 2;

        if (coordinates.x + radius + radiusSecondBall > other.coordinates.x
                && coordinates.x < other.coordinates.x + radius + radiusSecondBall
                && coordinates.y + radius + radiusSecondBall > other.coordinates.y
                && coordinates.y < other.coordinates.y + radius + radiusSecondBall) {
            if (this.distanceTo(other) < radius + radiusSecondBall) {
                calculateNewVelocities(other);
            }
        }
    }

    private Double distanceTo(Ball ballSecond) {
        double distance;
        distance = Math.sqrt(
                (this.coordinates.x - ballSecond.coordinates.x)
                        * (this.coordinates.x - ballSecond.coordinates.x)
                        + (this.coordinates.y - ballSecond.coordinates.y)
                        * (this.coordinates.y - ballSecond.coordinates.y));
        return distance;
    }

    public void setSpeed(int x, int y) {
        this.speed.x = x;
        this.speed.y = y;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public int getDiameter() {
        return diameter;
    }

    public Point getSpeed() {
        return speed;
    }

    public Point getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "coordinates=" + coordinates +
                ", speed=" + speed +
                '}';
    }
}
