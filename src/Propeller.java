import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Propeller extends Stuff{

    private final BufferedImage propeller;
    private final BufferedImage propeller1;
    private final BufferedImage propeller2;
    private final BufferedImage propeller3;

    private int startDistance;

    private final int totalDistance=160;


    {
        try {
            propeller = ImageIO.read(getClass().getResourceAsStream("objects/propeller/propeller.png"));
            propeller1 = ImageIO.read(getClass().getResourceAsStream("objects/propeller/propeller1.png"));
            propeller2 = ImageIO.read(getClass().getResourceAsStream("objects/propeller/propeller2.png"));
            propeller3 = ImageIO.read(getClass().getResourceAsStream("objects/propeller/propeller3.png"));


            image=propeller;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Propeller (){}

    public int getVelocityYToBeGiven() {
        return velocityToBeGiven.getY();
    }

    public void setVelocityYToBeGiven(int velocityYToBeGiven) {
        velocityToBeGiven.setY(velocityYToBeGiven); ;
    }

    Propeller(GamePanel gp){

        setGp(gp);

        setVelocityYToBeGiven(-5);

        int height=42;
        int width=42;


        mainRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(width*0,(int)(height*0.37),(int)(width*1),(int)(height*0.62));

        //size  -> width:32 height:32
        //solid -> x:0 y:12 width:32 height:20
        //scale -> x:0 y:0.37 width:1 height:0.62

        thisClonedAsset = new Propeller();
    }

    public Propeller getCloned(){
        thisClonedAsset.cloneParToThis(this);
        return (Propeller) thisClonedAsset;
    }
    @Override
    public void cloneParToThis(Asset asset) {
        Propeller propeller = (Propeller) asset;
        super.cloneParToThis(propeller);
        this.startDistance = propeller.startDistance;

    }
    @Override
    public void startInteraction() {

        if(connectedToDoodle){

            Asset otherAsset = doodle;
            Asset otherClonedAsset = otherAsset.getCloned();

            affect(otherAsset,otherClonedAsset);
            beAffected(otherAsset,otherClonedAsset);

        }
    }

    public void affect(Asset willBeAffected, Asset cloned) {

        if(willBeAffected instanceof Doodle){

            giveVelocity((Doodle) willBeAffected, (Doodle) cloned);

        }


    }


    public void beAffected(Asset affectedBy, Asset cloned) {

        /*if(affectedBy instanceof CanActivate){

            ((CanActivate) affectedBy).activate((CanBeActivated) this, (CanBeActivated) thisClonedAsset);
        }*/

        if(affectedBy instanceof CanLocate){
            ((CanLocate) affectedBy).locate((Stuff) this, (Stuff) thisClonedAsset);
        }



    }

    private void checkDistance(){
            Propeller cld = (Propeller) thisClonedAsset;
            if(cld.startDistance  - cld.getY()  > cld.totalDistance+300){
                connectedToDoodle=false;
            }


    }

    @Override
    public void giveVelocity(Doodle doodle,Doodle cloned) {

            Propeller cld = (Propeller) thisClonedAsset;

            Velocity velocityToBeGiven = new Velocity(cld.velocityToBeGiven);

            doodle.takeVelocityY(velocityToBeGiven, (Element) this, (Element) thisClonedAsset);




    }

    public void overFlowScreen() {

    }

    @Override
    public void beLocated(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {

        setLocation(x +20,y - getHeight());
    }

    @Override
    public void beLocatedX(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {

    }

    @Override
    public void beLocatedY(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {

    }

    @Override
    public void beHit(Hitter hitter, Hitter cloned) {

        Propeller cld = (Propeller) thisClonedAsset;

        if(hitter instanceof Doodle){
            startDistance = cld.getY();

            isThisSolid = false;

            connectedToDoodle=true;

            doodle = (Doodle) hitter;
            platform.setCointainingStuffToNull();
        }



    }

    @Override
    public void update() {
        super.update();

        checkDistance();
    }




}
