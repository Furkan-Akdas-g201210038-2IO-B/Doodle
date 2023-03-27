import java.awt.*;
import java.util.ArrayList;

public class AssetManager {
    GamePanel gp;
    ArrayList<Asset> assets = new ArrayList<>();
    Doodle doodle;
    ArrayList<Asset> steps = new ArrayList<>();

    ArrayList<Asset> elements = new ArrayList<>();
    AssetManager(GamePanel gp){
        this.gp=gp;
    }

    public Doodle createDoodle(){
        doodle = new Doodle(gp);

        assets.add(doodle);

        return doodle;
    }

    public Platform createStillPlatform(){
        Platform newPlatform = new Platform(gp);

        steps.add(newPlatform);
        assets.add(newPlatform);
        elements.add(newPlatform);

        return newPlatform;
    }

    public Platform createMoveblePlatform(){
        Platform newPlatform = new Platform(gp);

        newPlatform.moveAlongX();

        steps.add(newPlatform);
        assets.add(newPlatform);
        elements.add(newPlatform);

        return newPlatform;
    }

    public Platform createPlatform1(){
        Platform newPlatform = new Platform(gp);

        newPlatform.moveAlongX();

        steps.add(newPlatform);
        assets.add(newPlatform);
        elements.add(newPlatform);

        return newPlatform;
    }

    public Spring createSpring(){
        Spring newSpring = new Spring(gp);

        elements.add(newSpring);
        assets.add(newSpring);

        return newSpring;
    }

    public Doodle getDoodle(){
        return doodle;
    }

    public ArrayList<Asset> getSteps() {
        return steps;
    }

    public ArrayList<Asset> getElements() {
        return elements;
    }

    public void update(){

        for (Asset asset : assets)
            asset.update();

    }

    public void draw(Graphics2D g2){

        for (Asset asset : assets)
            asset.draw(g2);
    }

}
