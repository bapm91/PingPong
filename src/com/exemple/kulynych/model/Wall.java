package com.exemple.kulynych.model;

import java.awt.*;
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
        this.rectHeight = rectHeight;
        this.rectWidth = rectWidth;
        wallPosition();
        center();
        sides();
    }

    private void wallPosition() {
        Position position = new Position();
        wallPosition = position.position(world, rectWidth, rectHeight);
    }

    private void sides() {
        rightSide = wallPosition.x + rectWidth;
        topSide = wallPosition.y;
        leftSide = wallPosition.x;
        bottomSide = wallPosition.y + rectHeight;
    }

    private void center() {
        center.x = wallPosition.x + rectWidth;
        center.y = wallPosition.y + rectHeight;
    }

    public boolean contains(Ball ball) {
        int radius = ball.getDiametr() / 2;
        List<Point> list = new ArrayList<>();
        list.add(new Point(ball.getCenter().x + radius, ball.getCenter().y));
        list.add(new Point(ball.getCenter().x, ball.getCenter().y + radius));
        list.add(new Point(ball.getCenter().x, ball.getCenter().y - radius));
        list.add(new Point(ball.getCenter().x - radius, ball.getCenter().y));
        list.add(ball.getCenter());

        for (Point point : list) {
            if (point.x < rightSide
                    && point.x > leftSide
                    && point.y < bottomSide
                    && point.y > topSide) {
                return true;
            }
        }

        return false;
    }

    public boolean contains(Point point) {
        return point.x < rightSide
                && point.x > leftSide
                && point.y < bottomSide
                && point.y > topSide;
    }

    public Point getWallPosition() {
        return wallPosition;
    }

//    public Point getWallCenter() {
//        return center;
//    }

    public int getRectHeight() {
        return rectHeight;
    }

    public int getRectWidth() {
        return rectWidth;
    }

    public String relatively(Ball ball) {
        String torilebo = ""; // topRightLeftBottom
        Point ballCenter = ball.coordinates;
        if (ballCenter.x == this.center.x && ballCenter.y == this.center.y) {
            return "Right";
        }
        if (contains(ballCenter)) {
            if (ballCenter.x > this.center.x && ballCenter.y > this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) <= Math.abs(ballCenter.y - bottomSide)) {
                    torilebo = "Right";
                } else {
                    torilebo = "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y > this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) <= Math.abs(ballCenter.y - bottomSide)) {
                    torilebo = "Left";
                } else {
                    torilebo = "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) <= Math.abs(ballCenter.y - topSide)) {
                    torilebo = "Left";
                } else {
                    torilebo = "Top";
                }
            }
            if (ballCenter.x > this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) <= Math.abs(ballCenter.y - topSide)) {
                    torilebo = "Right";
                } else {
                    torilebo = "Top";
                }
            }
        }
        if (!contains(ballCenter) && contains(ball)) {
            if (ballCenter.x > this.center.x && ballCenter.y > this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) >= Math.abs(ballCenter.y - bottomSide)) {
                    torilebo = "Right";
                } else {
                    torilebo = "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y > this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) >= Math.abs(ballCenter.y - bottomSide)) {
                    torilebo = "Left";
                } else {
                    torilebo = "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) >= Math.abs(ballCenter.y - topSide)) {
                    torilebo = "Left";
                } else {
                    torilebo = "Top";
                }
            }
            if (ballCenter.x > this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) >= Math.abs(ballCenter.y - topSide)) {
                    torilebo = "Right";
                } else {
                    torilebo = "Top";
                }
            }
        }
        return torilebo;
    }
}
