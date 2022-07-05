import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Block extends Rectangle {

    Image pic;
    boolean destroyed;


    Block(int x, int y, int width, int height, String s) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        try {
            pic = ImageIO.read(new File("src/" + s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, Component c) {
        if (!destroyed) {
            g.drawImage(pic, x,y,width,height,c);
        }
    }
}
