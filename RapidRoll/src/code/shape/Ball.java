package code.shape;

import code.process.Shape;

import java.awt.*;

public class Ball extends Shape {

    public Ball() {
        this(0, 0, 0);
    }

    public Ball(double x, double y) {
        this(x, y, 0);
    }

    public Ball(double x, double y, double radius) {
        super(x, y, radius, radius);
        setColor(Color.RED);
    }

    public double getRadius() {
        return getHeight();
    }

    public void setRadius(double radius) {
        this.setWidth(radius);
        this.setHeight(radius);
    }

    @Override
    public String toString() {
        return "Ball: " + getX() + ", " + getY() ;
    }
}
