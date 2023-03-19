import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Asset {
    GamePanel gp;
    Rectangle screenRect;
    BufferedImage image;
    private int defaultVelocityY;
    private int defaultVelocityX;
    private int velocityX;
    private int velocityY;
    private int a;
    private int aWeakness;
    private int aCounter;


    public abstract void update();
    public abstract void draw(Graphics2D g2);


    /*private int spriteCounter=0;
    private int spriteTime=40;*/

}
