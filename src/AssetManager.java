import java.awt.*;
import java.util.ArrayList;

public class AssetManager {
    GamePanel gp;
    ArrayList<Asset> assets = new ArrayList<>();
    Doodle doodle;
    ArrayList<Asset> steps = new ArrayList<>();

    ArrayList<Platform> platforms = new ArrayList<>();

    ArrayList<Element> elements = new ArrayList<>();
    ArrayList<Stuff> stuffs = new ArrayList<>();
    private ArrayList<Propeller> propellers=new ArrayList<>();

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

        //newPlatform.moveAlongX();

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

    public Trampoline createTrampoline(){
        Trampoline trampoline = new Trampoline(gp);

        /*elements.add(newSpring);
        assets.add(newSpring);*/

        assets.add(trampoline);

        return trampoline;
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
        platforms.clear();

        for (Asset asset : assets){
            if(asset instanceof  Platform)
                 platforms.add((Platform) asset);
        }
        return platforms;
    }

    //public ArrayList<Asset> getSteps() {return steps;}

    public ArrayList<Asset> getSteps() {
        steps.clear();
        for (Asset asset : assets){
            if(asset instanceof  Platform)
                steps.add(asset);
        }

        return steps;

    }

    public ArrayList<Stuff> getStuffs() {
        stuffs.clear();
        for (Asset asset : assets){
            if(asset instanceof  Stuff)
                stuffs.add((Stuff) asset);
        }

        return stuffs;

    }
    //public ArrayList<Asset> getElements() {return elements;}

    public ArrayList<Element> getElements() {
        elements.clear();
        for (Asset asset : assets){

            if(asset instanceof Element){
               elements.add((Element) asset);
            }

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

        for (Asset asset : assets){
             asset.draw(g2);
        }

    }

    public void startInteraction(){

        for (Asset asset : getAssets()){
            asset.cloneClonedAsset();
        }


        for (Asset asset : assets){
            asset.startInteraction();
        }

    }

    public void overFlowScreen(){

        for (Asset asset : assets)
            asset.overFlowScreen();
    }

}
