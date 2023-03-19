import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Platform {

    private final Rectangle screenRect;

    private final BufferedImage image;

    private final Rectangle solidArea;

    private final int velocityToBeGiven = -8;
    private final int aToBeGiven = 1;

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public Rectangle getScreenRect(){return screenRect;}

    public int getVelocityToBeGiven() {
        return velocityToBeGiven;
    }
    public int getaToBeGiven() {
        return aToBeGiven;
    }

    /*private Rectangle cloneThisRectangle(Rectangle rectangle){
        return new Rectangle(rectangle.x, rectangle.y,rectangle.width,rectangle.height);
    }*/

    Platform(GamePanel gp){

       int height=23;
       int width=90;

        screenRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(0,0,width,height/4);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/platforms/greenPlatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(){

    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, screenRect.x, screenRect.y, screenRect.width, screenRect.height,null);
    }


}
