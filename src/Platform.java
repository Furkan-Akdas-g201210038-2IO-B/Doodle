import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Platform extends Element {

    Platform(Platform platform){
        super(platform);
    }
    Platform(){}
    Platform(GamePanel gp){

       setGp(gp);
       setVelocityYToBeGiven(-8);
       setAToBeGiven(1);
       int height=23;
       int width=90;

        mainRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(0,0,width,height/8);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/platforms/greenPlatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
