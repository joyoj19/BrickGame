import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks = new ArrayList<>();
    Block ball = new Block(237, 435, 25, 25, "ball.png");
    Block paddle = new Block(175, 480, 150, 25, "paddle.png");

    BlockBreakerPanel() {
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        paddle.draw(g, this);
        ball.draw(g, this);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void update() {

    }
}
