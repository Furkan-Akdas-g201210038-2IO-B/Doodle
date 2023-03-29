import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Doodle extends Asset implements VelocityTaker,CanActivate,CanLocate {

    private final BufferedImage rightImage;
    private final BufferedImage rightImage1;
    private final BufferedImage leftImage;
    private final BufferedImage leftImage1;

    private  int stopVelocity=0;
    private  int moveVelocity=5;

    private boolean takeVelocityYLock1=false;


    {
        try {
            leftImage = ImageIO.read(getClass().getResourceAsStream("/doodle/blue-lik-left.png"));
            rightImage = ImageIO.read(getClass().getResourceAsStream("/doodle/blue-lik-right.png"));
            rightImage1 = ImageIO.read(getClass().getResourceAsStream("/doodle/blue-lik-right-odskok.png"));
            leftImage1 = ImageIO.read(getClass().getResourceAsStream("/doodle/blue-lik-left-odskok.png"));

            image= rightImage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   Doodle(){}



    Doodle(GamePanel gp){

        setGp(gp);

        setVelocityY(-5);;

        setAY(1);
        setAWeaknessY(6);

        int height = 90;
        int width = 90;
        mainRect = new Rectangle(200,4500,width,height);

        //!!! mainrect in boyutu değiştiğinde foots un boyutu da değişmeli
        solidArea = new Rectangle((int)(width*0.27),(int)(height*0.86),(int)(width*0.45),(int)(width*0.13));

        //!!! mainrect in boyutu değiştiğinde boundary nin değerleri de değişmeli
        boundary = new Boundary(- mainRect.width/2,gp.gpWidth - mainRect.width/2,-1,-1);

        //width 62 height 60

        //foots width 28 height 8 x 16 y 52
        //scale width 0.45 height 0.13 x 0.25 y 0.86

        //head -> x:16 y:15 width:29 height:25
        //scale -> x:0.25 y:0.25 width:0.46 height:0.41

        thisClonedAsset=new Doodle();

    }


    @Override
    public void cloneParToThis(Asset asset) {
        Doodle doodle = (Doodle)asset;
        super.cloneParToThis(doodle);
        this.moveVelocity=doodle.moveVelocity;
        this.stopVelocity=doodle.stopVelocity;
        this.takeVelocityYLock1=doodle.takeVelocityYLock1;
    }

    public void clearCollidedAssets(){collidedAssets.clear();}
    public void addCollidedAsset(Asset collidedAsset){
        collidedAssets.add(collidedAsset);
    }

    public boolean headingDown(){
        return getVelocityY() > 0;
    }

    public boolean headingUp(){
        return getVelocityY() <= 0;
    }

    @Override
    public Doodle getCloned(){
        //thisClonedAsset.cloneParToThis(this);
        return (Doodle) thisClonedAsset;
    }

    @Override
    public void cloneClonedAsset() {
        thisClonedAsset.cloneParToThis(this);
    }

    public int getMoveVelocity() {
        return moveVelocity;
    }

    public void setMoveVelocity(int moveVelocity) {
        this.moveVelocity = moveVelocity;
    }



    public void executeWhenRightPressed(){

        //setVelocityX(moveVelocity);
        setAX(1);
        setAWeaknessX(4);

        if(headingUp())
            image = rightImage1;
        else if(headingDown())
            image = rightImage;

    }

    public void executeWhenLeftPressed(){

        //setVelocityX(- moveVelocity);
        setAX(-1);
        setAWeaknessX(4);

        if(headingUp())
            image = leftImage1;
        else if(headingDown())
            image = leftImage;

    }

    public void executeWhenNonePressed(){



        if(image.equals(rightImage) || image.equals(rightImage1)){

            if(headingUp())
                image = rightImage1;
            else if(headingDown())
                image = rightImage;

        }if(image.equals(leftImage) || image.equals(leftImage1)){

            if(headingUp())
                image = leftImage1;
            else if(headingDown())
                image = leftImage;

        }
    }

    public void executeWhenRightReleased(){

        setAX(0);


    }

    public void executeWhenLeftReleased() {

        setAX(0);


    }

    @Override
    public void update() {

        super.update();

        takeVelocityYLock1=false;
    }


    @Override
    public void startInteraction() {

        for(Asset collidedAsset : collidedAssets){

            /*thisAsset = this;
            thisClonedAsset.cloneParToThis(this);*/

            Asset otherAsset = collidedAsset;
            Asset otherClonedAsset = otherAsset.getCloned();


            affect(otherAsset,otherClonedAsset);
            //beAffected(otherAsset,otherClonedAsset);

        }



        for (Asset connectedAsset : connectedAssets){

           /* thisAsset = this;
            thisClonedAsset.cloneParToThis(this);*/

            Asset otherAsset = connectedAsset;
            Asset otherClonedAsset = otherAsset.getCloned();

            affect(otherAsset,otherClonedAsset);
            //beAffected(otherAsset,otherClonedAsset);

        }
    }

    @Override
    public void affect(Asset willBeAffected, Asset cloned) {

        if(willBeAffected instanceof CanBeActivated){
            activate((CanBeActivated) willBeAffected, (CanBeActivated) cloned);
        }

    }

    @Override
    public void beAffected(Asset affectedBy, Asset cloned) {
       /* if(affectedBy instanceof VelocityGiver){
            ((VelocityGiver) affectedBy).giveVelocity((VelocityTaker) thisAsset, (VelocityTaker) thisClonedAsset);
        }*/
    }

    @Override
    public void activate(CanBeActivated canBeActivated, CanBeActivated cloned) {

        if(canBeActivated instanceof Spring ||canBeActivated instanceof Propeller){

            if(((Doodle)thisClonedAsset).headingDown())
                canBeActivated.beActivated((CanActivate) this, (CanActivate) thisClonedAsset);

        }
        else {
            canBeActivated.beActivated((CanActivate) this, (CanActivate) thisClonedAsset);
        }

    }

    @Override
    public void locate(CanBeLocated canBeLocated, CanBeLocated cloned) {
        canBeLocated.beLocated(thisClonedAsset.getX(),thisClonedAsset.getY(),thisClonedAsset.getWidth(),thisClonedAsset.getHeight()
                ,(CanLocate) this, (CanLocate) thisClonedAsset);
    }

    @Override
    public void takeVelocity(Velocity givenVelocity, VelocityGiver velocityGiver, VelocityGiver cloned) {

        cloneVelocityToThisVelocity(givenVelocity);
    }

    @Override
    public void takeVelocityX(Velocity givenVelocityX, VelocityGiver velocityGiver, VelocityGiver cloned) {
        setVelocityX(givenVelocityX.getX());
    }
    @Override
    public void takeVelocityY(Velocity givenVelocityY, VelocityGiver velocityGiver, VelocityGiver cloned) {

        if(velocityGiver instanceof Platform){
            if(!takeVelocityYLock1)
                setVelocityY(givenVelocityY.getY());
        }else{
            setVelocityY(givenVelocityY.getY());
            takeVelocityYLock1=true;
        }

    }



    @Override
    public void overFlowScreen() {
        if(getX()  > getRight()){

            setLocation(getLeft(),getY());

        }else if(getX() <getLeft()){

            setLocation(getRight(),getY());
        }
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Doodle{" +
                ", stopVelocity=" + stopVelocity +
                ", moveVelocity=" + moveVelocity +
                '}';
    }


}
