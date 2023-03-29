import java.awt.*;
import java.util.ArrayList;

public class AssetManager {
    GamePanel gp;
    ArrayList<Asset> assets = new ArrayList<>();
    Doodle doodle;
    ArrayList<Asset> steps = new ArrayList<>();

    ArrayList<Platform> platforms = new ArrayList<>();

    ArrayList<Asset> elements = new ArrayList<>();
    ArrayList<Asset> stuffs = new ArrayList<>();


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

        /*steps.add(newPlatform);
        assets.add(newPlatform);
        elements.add(newPlatform);*/

        assets.add(newPlatform);

        return newPlatform;
    }

    public Platform createMoveblePlatform(){
        Platform newPlatform = new Platform(gp);

        newPlatform.moveAlongX();

       /* steps.add(newPlatform);
        assets.add(newPlatform);
        elements.add(newPlatform);*/

        assets.add(newPlatform);

        return newPlatform;
    }

    public Platform createPlatform1(){
        Platform newPlatform = new Platform(gp);

        newPlatform.moveAlongX();

        /*steps.add(newPlatform);
        assets.add(newPlatform);
        elements.add(newPlatform);*/

        assets.add(newPlatform);

        return newPlatform;
    }

    public Spring createSpring(){
        Spring newSpring = new Spring(gp);

        /*elements.add(newSpring);
        assets.add(newSpring);*/

        assets.add(newSpring);

        return newSpring;
    }
    public Propeller createPropeller(){
        Propeller propeller = new Propeller(gp);

        /*elements.add(newSpring);
        assets.add(newSpring);*/

        assets.add(propeller);

        return propeller;
    }


    //public Doodle getDoodle(){return  doodle;}
    public Doodle getDoodle(){

        Doodle doodle=null;

        for (Asset asset : assets){
            if(asset instanceof  Doodle)
                doodle = (Doodle) asset;
        }
        return doodle;
    }

    public ArrayList<Platform> getPlatforms(){

        for (Asset asset : assets){
            if(asset instanceof  Platform)
                 platforms.add((Platform) asset);
        }
        return platforms;
    }

    //public ArrayList<Asset> getSteps() {return steps;}

    public ArrayList<Asset> getSteps() {

        for (Asset asset : assets){
            if(asset instanceof  Platform)
                steps.add(asset);
        }

        return steps;

    }

    public ArrayList<Asset> getStuffs() {

        for (Asset asset : assets){
            if(asset instanceof  Propeller || asset instanceof Spring)
                stuffs.add(asset);
        }

        return stuffs;

    }
    //public ArrayList<Asset> getElements() {return elements;}

    public ArrayList<Asset> getElements() {

        for (Asset asset : assets){
            if(asset instanceof  Platform || asset instanceof  Spring || asset instanceof Propeller)
                elements.add(asset);
        }

        return elements;
    }

    public ArrayList<Asset> getAssets() {
        return assets;
    }

    public void update(){

        for (Asset asset : assets)
            asset.update();

    }

    public void draw(Graphics2D g2){

        for (Asset asset : assets)
            asset.draw(g2);
    }

    public void startInteraction(){

        for (Asset asset : getAssets()){
            asset.cloneClonedAsset();
        }


        for (Asset canInteract : assets)
            canInteract.startInteraction();


    }

    public void overFlowScreen(){

        for (Asset asset : assets)
            asset.overFlowScreen();
    }

}
