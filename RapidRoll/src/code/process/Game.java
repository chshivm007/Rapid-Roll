package code.process;

import code.Direction;
import code.exceptions.GameOverException;
import code.shape.Ball;
import code.shape.Bar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    private static Random random = new Random();

    private static final int BALL_SIZE = 12;
    private static final int BAR_HEIGHT = 10;
    private static final int BAR_WIDTH = 80;
    private static final int BAR_COUNT = 8;

    double score = 0;
    int lives = 3;

    public static final int BAR_GAP = 40;
    private boolean control  =  false;
    private Direction direction = Direction.LEFT;

    Ball ball;
    ArrayList<Bar> bars;
    Dimension windowSize;

    public Game(Dimension windowSize) {
        this.windowSize = windowSize;

        bars = new ArrayList<>();
        ball = new Ball();

        ball.setX(windowSize.width / 2);
        ball.setY(0);
        ball.setRadius(BALL_SIZE);

        for (int i = 0; i < BAR_COUNT; ++i) {
            int x = random.nextInt(windowSize.width - BAR_WIDTH);
            int y = 2 * BAR_GAP + i * (BAR_GAP + BAR_HEIGHT);
            bars.add(new Bar(x, y, BAR_WIDTH, BAR_HEIGHT));
        }
    }

    //MOVEMENT
    public void update() throws GameOverException {

        if (ball.getY() < 0 || ball.getY() + 3 * BALL_SIZE > windowSize.getHeight()) {
            if (lives > 0) {
                --lives;
                ball.setX(windowSize.width / 2);
                ball.setY(BAR_GAP);
            }
            else
                throw new GameOverException("Your Score is: " + (int) score);
        }
        score += 0.1;
        Direction verticalDirection = Direction.DOWN;

        for (Bar bar : bars) {
            bar.move(Direction.UP);
            if (bar.hasBall(ball))
                verticalDirection = Direction.UP;
        }

        if (bars.get(0).getY() + BAR_HEIGHT < 0) {
            Bar removed = bars.remove(0);

            removed.setY(windowSize.height);
            removed.setX(random.nextInt(windowSize.width - BAR_WIDTH));
            bars.add(removed);
        }
        ball.move(verticalDirection);

        if (control) {
            if (ball.getX() < 0  && direction == Direction.LEFT) {

            }
            else if (ball.getX() + ball.getRadius() > windowSize.getWidth()  && direction ==  Direction.RIGHT) {

            }
            else {
                ball.move(direction, (int)ball.getRadius() / 5);
            }
        }
    }

    public void moveBall(Direction direction) {
        control = true;
        this.direction = direction;
    }

    public void stopBall() {
        control = false;
    }
}
