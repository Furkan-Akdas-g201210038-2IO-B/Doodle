import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spring extends Element {

    private final BufferedImage comp;
    private final BufferedImage unComp;

    {
        try {
            comp = ImageIO.read(getClass().getResourceAsStream("/objects/spring_comp.png"));
            unComp = ImageIO.read(getClass().getResourceAsStream("/objects/spring.png"));

            image=comp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Spring (){}

    Spring(GamePanel gp){

        setGp(gp);
        setVelocityYToBeGiven(-11);
        setAToBeGiven(1);

        int height=22;
        int width=30;

        mainRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(width*0,(int)(height*0.38),(int)(width*1),(int)(height*0.40));

        //size  -> width:21 height:44
        //solid -> x:0 y:17 width:21 height:18
        //scale -> x:0 y:0.38 width:1 height:0.40

    }

}
