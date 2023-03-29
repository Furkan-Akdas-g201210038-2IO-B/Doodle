import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Platform extends VelocityGiver implements CanBeActivated,CanLocate{

    Platform(Platform platform){
        super(platform);
    }
    Platform(){}


    private  int stopVelocity=0;
    private  int moveVelocityX=3;

    private int solidness=-1;
    Platform(GamePanel gp){

       setGp(gp);
       setAY(0);

       setVelocityYToBeGiven(-7);


       int height=23;
       int width=90;


       mainRect = new Rectangle(0,0,width,height);
       solidArea = new Rectangle(0,0,width,height/8);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/platforms/greenPlatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boundary = new Boundary(0,gp.gpWidth-getWidth(),-1,-1);

        thisClonedAsset = new Platform();

    }
    @Override
    public Platform getCloned(){

        thisClonedAsset.cloneParToThis(this);

        return (Platform) thisClonedAsset;
    }


    @Override
    public void cloneParToThis(Asset asset) {
        Platform platform = (Platform)asset;

        super.cloneParToThis(platform);
        this.solidness= platform.solidness;
        this.stopVelocity= platform.stopVelocity;
        this.moveVelocityX= platform.moveVelocityX;
    }

    public void setSolidness(int solidness) {
        this.solidness = solidness;
    }

    public int getSolidness() {
        return solidness;
    }

    public void moveAlongX(){
        setVelocityX(moveVelocityX);
    }

    public int getMoveVelocityX() {
        return moveVelocityX;
    }

    public void setMoveVelocityX(int moveVelocityX) {
        this.moveVelocityX = moveVelocityX;
    }

    @Override
    public void update() {
        super.update();
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

        if(willBeAffected instanceof CanBeLocated){
            locate((CanBeLocated) willBeAffected, (CanBeLocated) cloned);
        }

    }

    @Override
    public void beAffected(Asset affectedBy, Asset cloned) {

        if(affectedBy instanceof CanActivate){

            ((CanActivate) affectedBy).activate((CanBeActivated) thisAsset, (CanBeActivated) thisClonedAsset);
        }
    }



    @Override
    public void giveVelocity(VelocityTaker velocityTaker ,VelocityTaker cloned) {

       // System.out.println(((Platform)thisClonedAsset).getVelocityYToBeGiven());

        Velocity velocityToBeGiven = new Velocity(((Platform)(thisClonedAsset)).velocityToBeGiven);



        if(velocityTaker instanceof Doodle){
            if(((Doodle) cloned).headingDown())
                velocityTaker.takeVelocityY(velocityToBeGiven);
        }
    }

    @Override
    public void locate(CanBeLocated canBeLocated, CanBeLocated cloned) {
        //Şart yok
        canBeLocated.beLocated(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void beActivated(CanActivate canActivate, CanActivate cloned) {

    }


    @Override
    public void overFlowScreen() {
        if(getX()  > getRight()){

            setLocation(getRight(),getY());

            setVelocityX(-Math.abs(getVelocityX()));

        }else if(getX() < -getLeft()){

            setLocation(-getLeft(),getY());

            setVelocityX(Math.abs(getVelocityX()));
        }
    }

    @Override
    public String toString() {
        return super.toString()+
                "Platform{" +
                "stopVelocity=" + stopVelocity +
                ", moveVelocityX=" + moveVelocityX +
                ", solidness=" + solidness +
                '}';
    }
}
