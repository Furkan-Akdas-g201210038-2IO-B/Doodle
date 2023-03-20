import java.util.ArrayList;

public class Observer {

    private GamePanel gp;
    private Doodle doodle;
    private ArrayList<Platform> platforms;
    public int earnedVelocity;
    public int earnedA;

   // AssetManager assetManager;

    Observer(GamePanel gp){

        this.gp=gp;
        doodle = this.gp.assetManager.doodle;
        platforms = this.gp.assetManager.platforms;
    }

    public void observe(){

        earnedVelocity=0;
        earnedA=1;

        int tempXDoodle=doodle.getFoots().x;
        int tempYDoodle=doodle.getFoots().y;

        doodle.getFoots().x =doodle.getFoots().x + doodle.getMainRect().x;
        doodle.getFoots().y =doodle.getFoots().y + doodle.getMainRect().y;

        for (Platform platform : platforms){

            int tempXPlatform = platform.getSolidArea().x;
            int tempYPlatform = platform.getSolidArea().y;

            platform.getSolidArea().x = platform.getMainRect().x + platform.getSolidArea().x;
            platform.getSolidArea().y = platform.getMainRect().y + platform.getSolidArea().y;

            if(doodle.getFoots().intersects(platform.getSolidArea())){
                earnedVelocity = platform.getVelocityToBeGiven();
                earnedA = platform.getAToBeGiven();

            }

            platform.getSolidArea().x = tempXPlatform;
            platform.getSolidArea().y = tempYPlatform;

        }

        doodle.getFoots().x =tempXDoodle;
        doodle.getFoots().y =tempYDoodle;

    }

}
