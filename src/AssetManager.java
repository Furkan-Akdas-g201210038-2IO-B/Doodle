import java.awt.*;
import java.util.ArrayList;

public class AssetManager {

    GamePanel gp;
    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Spring> springs = new ArrayList<>();
    private final int platformNum =8;
    final Doodle doodle;

    private AssetManager(Doodle doodle,ArrayList<Platform> platforms){
        this.doodle=doodle;
        this.platforms=platforms;
    }
    AssetManager(GamePanel gp){
        this.gp=gp;
        doodle = new Doodle(gp);
        createPlatforms(gp);
    }
    private void createPlatforms(GamePanel gp) {
        for (int i = 0; i< platformNum; i++){
            platforms.add(new Platform(gp));
        }
    }

    /*public AssetManager cloneThis(){
        //return new AssetManager()
    }*/

    public void update(){

        for(Platform platform : platforms){
            platform.update();
        }

        doodle.update();

    }

    public void draw(Graphics2D g2){

        for(Platform platform : platforms){
            platform.draw(g2);
        }

        doodle.draw(g2);

    }

}
