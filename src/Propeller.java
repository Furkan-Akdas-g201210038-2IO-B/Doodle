import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Propeller extends VelocityGiver implements CanBeActivated,CanBeLocated{

    private final BufferedImage propeller;
    private final BufferedImage propeller1;
    private final BufferedImage propeller2;
    private final BufferedImage propeller3;

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




    Propeller(GamePanel gp){

        setGp(gp);

        setVelocityYToBeGiven(-5);

        int height=32;
        int width=32;


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

    }
    @Override
    public void startInteraction() {

        for (Asset collidedAsset : collidedAssets){

            thisAsset = this;
            thisClonedAsset.cloneParToThis(this);

            Asset otherAsset = collidedAsset;
            Asset otherClonedAsset = otherAsset.getCloned();

            affect(otherAsset,otherClonedAsset);
            beAffected(otherAsset,otherClonedAsset);

        }

        clearCollidedAssets();

        for (Asset connectedAsset : connectedAssets){

            thisAsset = this;
            thisClonedAsset.cloneParToThis(this);

            Asset otherAsset = connectedAsset;
            Asset otherClonedAsset = otherAsset.getCloned();

            affect(otherAsset,otherClonedAsset);
            beAffected(otherAsset,otherClonedAsset);

        }

    }

    @Override
    public void affect(Asset willBeAffected, Asset cloned) {

        if(willBeAffected instanceof VelocityTaker){

            giveVelocity((VelocityTaker) willBeAffected, (VelocityTaker) cloned);

        }


    }


    @Override
    public void beAffected(Asset affectedBy, Asset cloned) {

        if(affectedBy instanceof CanActivate){

            ((CanActivate) affectedBy).activate((CanBeActivated) thisAsset, (CanBeActivated) thisClonedAsset);
        }

        if(affectedBy instanceof CanLocate){
            ((CanLocate) affectedBy).locate((CanBeLocated) thisAsset, (CanBeLocated) thisClonedAsset);
        }

    }

    @Override
    public void giveVelocity(VelocityTaker velocityTaker, VelocityTaker cloned) {

        Velocity velocityToBeGiven = new Velocity(((Propeller)(thisClonedAsset)).velocityToBeGiven);

        velocityTaker.takeVelocityY(velocityToBeGiven);

    }

    @Override
    public void overFlowScreen() {

    }

    @Override
    public void beLocated(int x, int y, int width, int height) {
        setLocation(x,y+60);
    }
//a
    @Override
    public void beLocatedX(int x, int y, int width, int height) {

    }

    @Override
    public void beLocatedY(int x, int y, int width, int height) {

    }

    @Override
    public void beActivated(CanActivate canActivate, CanActivate cloned) {

    }
}
