package com.exemple.kulynych.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Wall {
    private World world;
    private Point wallPosition;
    private int rectHeight;
    private int rectWidth;
    private int rightSide;
    private int topSide;
    private int leftSide;
    private int bottomSide;
    private Point center = new Point(0, 0);

    public Wall(World world, int rectWidth, int rectHeight) {
        this.world = world;
        this.rectWidth = rectWidth;
        this.rectHeight = rectHeight;
        wallPosition();
        center();
        sides();
    }

    private void wallPosition() {
        Position position = new Position();
        wallPosition = new Point(0, 0);
        wallPosition = position.position(world, rectWidth, rectHeight);
    }

    private void center() {
        center.x = wallPosition.x + rectWidth / 2;
        center.y = wallPosition.y + rectHeight / 2;
    }

    private void sides() {
        rightSide = wallPosition.x + rectWidth;
        topSide = wallPosition.y;
        leftSide = wallPosition.x;
        bottomSide = wallPosition.y + rectHeight;
    }

    public boolean contains(Ball ball) {
        int radius = ball.getDiameter() / 2;
        List<Point> list = new ArrayList<>();
        list.add(new Point(ball.getCenter().x + radius, ball.getCenter().y));
        list.add(new Point(ball.getCenter().x, ball.getCenter().y + radius));
        list.add(new Point(ball.getCenter().x, ball.getCenter().y - radius));
        list.add(new Point(ball.getCenter().x - radius, ball.getCenter().y));
        list.add(ball.getCenter());

        for (Point point : list) {
            if (contains(point)) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(Point point) {
        return point.x <= rightSide
                && point.x >= leftSide
                && point.y <= bottomSide
                && point.y >= topSide;
    }

    public String relatively(Ball ball) {

        if (!this.contains(ball)) {
            return "Do nothing";
        }

        Point ballCenter = ball.getCenter();
        if (ballCenter.x == this.center.x && ballCenter.y == this.center.y) {
            return "Right";
        }
        if (contains(ballCenter)) {
            if (ballCenter.x >= this.center.x && ballCenter.y >= this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) <= Math.abs(ballCenter.y - bottomSide)) {
                    return "Right";
                } else {
                    return "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y >= this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) <= Math.abs(ballCenter.y - bottomSide)) {
                    return "Left";
                } else {
                    return "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) <= Math.abs(ballCenter.y - topSide)) {
                    return "Left";
                } else {
                    return "Top";
                }
            }
            if (ballCenter.x >= this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) <= Math.abs(ballCenter.y - topSide)) {
                    return "Right";
                } else {
                    return "Top";
                }
            }
        }
        if (!contains(ballCenter)) {
            if (ballCenter.x >= this.center.x && ballCenter.y >= this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) >= Math.abs(ballCenter.y - bottomSide)) {
                    return "Right";
                } else {
                    return "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y >= this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) >= Math.abs(ballCenter.y - bottomSide)) {
                    return "Left";
                } else {
                    return "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) >= Math.abs(ballCenter.y - topSide)) {
                    return "Left";
                } else {
                    return "Top";
                }
            }
            if (ballCenter.x >= this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) >= Math.abs(ballCenter.y - topSide)) {
                    return "Right";
                } else {
                    return "Top";
                }
            }
        }
        return "Do nothing";
    }

    public Point getWallPosition() {
        return wallPosition;
    }

    public int getRectHeight() {
        return rectHeight;
    }

    public int getRectWidth() {
        return rectWidth;
    }
}
