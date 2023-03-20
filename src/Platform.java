import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Platform extends Asset {

    private final Rectangle solidArea;

    private final int velocityToBeGiven = -8;
    private final int aToBeGiven = 1;


    /*private Rectangle cloneThisRectangle(Rectangle rectangle){
        return new Rectangle(rectangle.x, rectangle.y,rectangle.width,rectangle.height);
    }*/

    Platform(GamePanel gp){
       super(gp,null,null,0,0,0,0,0,0,0);
       int height=23;
       int width=90;

        mainRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(0,0,width,height/4);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/platforms/greenPlatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public int getVelocityToBeGiven() {
        return velocityToBeGiven;
    }
    public int getAToBeGiven() {
        return aToBeGiven;
    }
    @Override
    public void update(){

    }
    @Override
    public void draw(Graphics2D g2){
        g2.drawImage(image, mainRect.x, mainRect.y, mainRect.width, mainRect.height,null);
    }


}
