package code.process;

import code.Direction;

import java.awt.*;

public abstract class Shape {
    private double x;
    private double y;
    private double height;
    private double width;
    private Color color;

    private static final double SPEED = 1;

    public Shape(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP -> {
                y -= SPEED;
            }
            case DOWN -> {
                y += SPEED;
            }
            case LEFT -> {
                x -= SPEED;
            }
            case RIGHT -> {
                x += SPEED;
            }
        }
    }
    public void move(Direction direction, int steps) {
        switch (direction) {
            case UP -> {
                y -= steps;
            }
            case DOWN -> {
                y += steps;
            }
            case LEFT -> {
                x -= steps;
            }
            case RIGHT -> {
                x += steps;
            }
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
