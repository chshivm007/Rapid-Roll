package code.process;

import code.Direction;
import code.exceptions.GameOverException;
import code.shape.Bar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen extends JPanel implements KeyListener {
    Game game;
    Timer screenTimer;
    Timer updateTimer;
    BufferedImage img;
    Point life;

    private final int LIFE_GAP = 5;
    private final int LIFE_SIZE = 25;

    public Screen(Dimension windowSize) {
        setPreferredSize(windowSize);
        game = new Game(getPreferredSize());
        setFocusable(true);
        requestFocusInWindow();
        life = new Point(getPreferredSize().width, LIFE_GAP);
        System.out.println(getWidth() + " POINT XY = " + life);


        screenTimer = new Timer(1, e -> {
            repaint();
        });

        updateTimer = new Timer(20, e -> {
            try {
                game.update();
            } catch (GameOverException gameOverException) {

                updateTimer.stop();
                screenTimer.stop();

                JOptionPane.showMessageDialog(this, gameOverException.getMessage());
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        });

        screenTimer.start();
        updateTimer.start();
        addKeyListener(this);
        try {
            img = ImageIO.read(new File("life.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Score : " + (int)game.score, 5 , 20);

        for (int i = 1; i <= game.lives; ++i) {
            g.drawImage(img, (int)life.getX() - i * (LIFE_SIZE + LIFE_GAP), life.y, LIFE_SIZE - 10, LIFE_SIZE, null);
        }

        g.setColor(game.ball.getColor());
        g.drawOval((int) game.ball.getX(), (int) game.ball.getY(), (int) game.ball.getWidth(), (int) game.ball.getHeight());
        g.fillOval((int) game.ball.getX(), (int) game.ball.getY(), (int) game.ball.getWidth(), (int) game.ball.getHeight());

        for (Bar bar : game.bars) {
            g.setColor(bar.getColor());
            g.drawRoundRect((int) bar.getX(), (int) bar.getY(), (int) bar.getWidth(), (int) bar.getHeight(), 5, 5);
            g.fillRoundRect((int) bar.getX(), (int) bar.getY(), (int) bar.getWidth(), (int) bar.getHeight(), 5, 5);
        }
    }

    public Timer getScreenTimer() {
        return screenTimer;
    }

    public Timer getUpdateTimer() {
        return updateTimer;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39 -> {
                game.moveBall(Direction.RIGHT);
            }
            case 37 -> {
                game.moveBall(Direction.LEFT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39, 37 -> {
                game.stopBall();
            }
        }
    }
}
