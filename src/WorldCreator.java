import java.awt.*;
import java.util.Random;

public class WorldCreator {


    private GamePanel gp;

    private Random random = new Random();

    private int cursorYPlatform;
    private final int marginYPlatform;
    public WorldCreator(GamePanel gp){

        this.gp=gp;

        cursorYPlatform=0;
        marginYPlatform=100;

    }

    public void placePlatforms() {

        for (Platform platform : gp.assetManager.platforms){
            int randomX = random.nextInt(gp.screenWidth - platform.getMainRect().width);
            platform.getMainRect().setLocation(new Point(randomX,cursorYPlatform));

            cursorYPlatform+=marginYPlatform;
        }

    }


}
