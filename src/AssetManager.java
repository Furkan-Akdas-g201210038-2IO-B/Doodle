import java.awt.*;
import java.util.ArrayList;

public class AssetManager {
    GamePanel gp;
    ArrayList<Platform> platforms = new ArrayList<>();
  //  ArrayList<Spring> springs = new ArrayList<>();
    final Doodle doodle;
    ArrayList<Element> elements = new ArrayList<>();
    private final int platformNum =8;


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
            Platform platform = new Platform(gp);
            platforms.add(platform);
            elements.add(platform);
        }

    }

    public Doodle getDoodle(){
        return doodle;
    }

    public ArrayList<Element> getElements(){
        return elements;
    }

   /* public Doodle getClonedDoodle(){
        Doodle clonedDoodle = new Doodle(doodle);

        return clonedDoodle;
    }*/


   /* public ArrayList<Element> getClonedElements(){

        ArrayList<Element> clonedElements= new ArrayList<>();

        return clonedElements;
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
