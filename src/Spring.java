import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spring {


    private final Rectangle screenRect;

    private final BufferedImage image;
    private final BufferedImage image1;

    private final Rectangle solidArea;

    private final int solidness = -20;

    private boolean compressed = true;

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public Rectangle getScreenRect(){return screenRect;}

    public int getSolidness() {
        return solidness;
    }

    /*private Rectangle cloneThisRectangle(Rectangle rectangle){
        return new Rectangle(rectangle.x, rectangle.y,rectangle.width,rectangle.height);
    }*/

    Spring(GamePanel gp){

        int height=22;
        int width=30;

        screenRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(width*0,(int)(height*0.38),(int)(width*1),(int)(height*0.40));

        //size  -> width:21 height:44
        //solid -> x:0 y:17 width:21 height:18
        //scale -> x:0 y:0.38 width:1 height:0.40


        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spring_comp.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/spring.png"));
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
