import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks = new ArrayList<>();
    Block ball = new Block(237, 435, 35, 25, "ball.png");
    Block paddle = new Block(175, 480, 150, 25, "paddle.png");

    BlockBreakerPanel() {

        for (int i = 0; i< 8; i++)
            blocks.add(new Block((i*60+3), 0, 60, 25, "blue.png"));
        for (int i = 0; i< 8; i++)
            blocks.add(new Block((i*60+3), 25, 60, 25, "green.png"));
        for (int i = 0; i< 8; i++)
            blocks.add(new Block((i*60+3), 50, 60, 25, "yellow.png"));
        for (int i = 0; i< 8; i++)
            blocks.add(new Block((i*60+3), 75, 60, 25, "red.png"));


        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        blocks.forEach(block -> {
            block.draw(g,this);
        });
        paddle.draw(g, this);
        ball.draw(g, this);
    }

    public void update() {
        ball.x += ball.movX;

        if (ball.x > (getWidth() - 25) || ball.x < 0)
            ball.movX *= -1;

        if (ball.y < 0 || ball.intersects(paddle))
            ball.movY *= -1;

        ball.y += ball.movY;

        blocks.forEach(block -> {
            if (ball.intersects(block) && !block.destroyed) {
                block.destroyed = true;
                ball.movY *= -1;
            }
        });
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            new Thread(() -> {
                while (true) {
                    update();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException error) {
                        error.printStackTrace();
                    }
                }
            }) .start();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            paddle.x += 15;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            paddle.x -= 15;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
