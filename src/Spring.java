import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spring extends VelocityGiver implements CanBeActivated ,CanBeLocated{

    private final BufferedImage comp;
    private final BufferedImage unComp;

    private boolean canGiveVelocity=true;

    private boolean locatedLock1;

    {
        try {
            comp = ImageIO.read(getClass().getResourceAsStream("/objects/spring/spring_comp.png"));
            unComp = ImageIO.read(getClass().getResourceAsStream("/objects/spring/spring.png"));

            image=comp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Spring (){}




    Spring(GamePanel gp){

        setGp(gp);

        setVelocityYToBeGiven(-5);

        int height=30;
        int width=30;

        /*int height=22;
        int width=30;*/

        mainRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(width*0,(int)(height*0.38),(int)(width*1),(int)(height*0.40));

        //size  -> width:21 height:44
        //solid -> x:0 y:17 width:21 height:18
        //scale -> x:0 y:0.38 width:1 height:0.40



        thisClonedAsset = new Spring();
    }
    @Override
    public Spring getCloned(){
        //thisClonedAsset.cloneParToThis(this);
        return (Spring) thisClonedAsset;
    }

    @Override
    public void cloneClonedAsset() {
        thisClonedAsset.cloneParToThis(this);
    }

    @Override
    public void cloneParToThis(Asset asset) {
        Spring spring = (Spring)asset;
        super.cloneParToThis(spring);
        this.canGiveVelocity=spring.canGiveVelocity;
        this.locatedLock1 = spring.locatedLock1;
    }


    @Override
    public void update() {
        super.update();
        locatedLock1=false;
    }

    @Override
    public void startInteraction() {

        for (Asset collidedAsset : collidedAssets){

            /*thisAsset = this;
            thisClonedAsset.cloneParToThis(this);*/

            Asset otherAsset = collidedAsset;
            Asset otherClonedAsset = otherAsset.getCloned();

            affect(otherAsset,otherClonedAsset);
            //beAffected(otherAsset,otherClonedAsset);

        }


        for (Asset connectedAsset : connectedAssets){

            /*thisAsset = this;
            thisClonedAsset.cloneParToThis(this);*/

            Asset otherAsset = connectedAsset;
            Asset otherClonedAsset = otherAsset.getCloned();

            affect(otherAsset,otherClonedAsset);
            //beAffected(otherAsset,otherClonedAsset);

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

        /*if(affectedBy instanceof CanActivate){

            ((CanActivate) affectedBy).activate((CanBeActivated) thisAsset, (CanBeActivated) thisClonedAsset);
        }

        if(affectedBy instanceof CanLocate){
            ((CanLocate) affectedBy).locate((CanBeLocated) thisAsset, (CanBeLocated) thisClonedAsset);
        }*/

    }

    @Override
    public void giveVelocity(VelocityTaker velocityTaker,VelocityTaker cloned) {

        if(canGiveVelocity){



            Velocity velocityToBeGiven = new Velocity(((Spring)(thisClonedAsset)).velocityToBeGiven);

            if(velocityTaker instanceof Doodle){
                if(((Doodle) cloned).headingDown())
                    velocityTaker.takeVelocityY(velocityToBeGiven, (VelocityGiver) this, (VelocityGiver) thisClonedAsset);
            }




        }



    }


    @Override
    public void beActivated(CanActivate canActivate, CanActivate cloned) {

       setImage(unComp);

       canGiveVelocity=false;
    }


    @Override
    public void beLocated(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {

        if(!(canLocate instanceof Platform)){
            if(!locatedLock1)
                setLocation(x + width/2,y - getHeight());
        }else {
            setLocation(x + width/2,y - getHeight());
            locatedLock1=true;
        }


    }

    @Override
    public void beLocatedX(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {
        // setLocation(x + width/2,y - getHeight());
    }

    @Override
    public void beLocatedY(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {
        //setLocation(x + width/2,y - getHeight());
    }

    @Override
    public void overFlowScreen() {

    }


    @Override
    public String toString() {
        return super.toString()+
                "Spring{" +
                ", canGiveVelocity=" + canGiveVelocity +
                '}';
    }
}
