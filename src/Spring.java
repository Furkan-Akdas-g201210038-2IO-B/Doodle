import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spring extends Asset {

    private final BufferedImage comp;
    private final BufferedImage unComp;

    private final Rectangle solidArea;

    private final int velocityToBeGiven = -8;
    private final int aToBeGiven = 1;

    private boolean compressed = true;

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getVelocityToBeGiven() {
        return velocityToBeGiven;
    }

    public int getaToBeGiven() {
        return aToBeGiven;
    }

    Spring(GamePanel gp){
        super(gp,null,null,0,0,0,0,0,0,0);
        int height=22;
        int width=30;

        mainRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(width*0,(int)(height*0.38),(int)(width*1),(int)(height*0.40));

        //size  -> width:21 height:44
        //solid -> x:0 y:17 width:21 height:18
        //scale -> x:0 y:0.38 width:1 height:0.40


        try {
            comp = ImageIO.read(getClass().getResourceAsStream("/objects/spring_comp.png"));
            unComp = ImageIO.read(getClass().getResourceAsStream("/objects/spring.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(){



    }

    @Override
    public void draw(Graphics2D g2){
        g2.drawImage(image, mainRect.x, mainRect.y, mainRect.width, mainRect.height,null);
    }



}
