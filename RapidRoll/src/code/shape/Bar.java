package code.shape;

import code.process.Shape;

import java.awt.*;

public class Bar extends Shape {

    public Bar() {
        this(0, 0, 0, 0);
    }

    public Bar(double x, double y) {
        this(x, y, 0, 0);
    }

    public Bar(double x, double y, double length, double breadth) {
        super(x, y, length, breadth);
        setColor(Color.BLUE);
    }
    public boolean hasBall(Ball ball) {
        // ballCurrentPos + radius + 2unit  if contains in bar... return true ... else false
        return ((ball.getX() + ball.getRadius() >= getX() && ball.getX() <= getX() + getWidth()) && (ball.getY() + ball.getRadius() <= getY() && ball.getY() + ball.getRadius() >= getY() - 2));
    }
}
