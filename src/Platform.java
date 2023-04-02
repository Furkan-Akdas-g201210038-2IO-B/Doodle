import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Platform extends Element implements CanLocate{

    Platform(){}

    private  int stopVelocity=0;
    private  int moveVelocityX=3;

    private int solidness=-1;

    private Stuff cointainingStuff;

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

        //thisClonedAsset.cloneParToThis(this);

        return (Platform) thisClonedAsset;
    }

    public boolean doesCointainStuff(){
        return cointainingStuff !=null;
    }

    @Override
    public void cloneClonedAsset() {
        thisClonedAsset.cloneParToThis(this);
    }

    @Override
    public void cloneParToThis(Asset asset) {
        Platform platform = (Platform)asset;

        super.cloneParToThis(platform);
        this.solidness= platform.solidness;
        this.stopVelocity= platform.stopVelocity;
        this.moveVelocityX= platform.moveVelocityX;
        this.cointainingStuff= platform.cointainingStuff;
    }

    public Stuff getCointainingStuff() {
        return cointainingStuff;
    }

    public void setCointainingStuff(Stuff cointainingStuff) {
        this.cointainingStuff = cointainingStuff;
    }

    public void setCointainingStuffToNull(Stuff stuff){
        cointainingStuff = null;
    }

    public int getVelocityYToBeGiven() {
        return velocityToBeGiven.getY();
    }

    public void setVelocityYToBeGiven(int velocityYToBeGiven) {
        velocityToBeGiven.setY(velocityYToBeGiven); ;
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


        if(cointainingStuff != null){

            Stuff clonedCointainingStuff = (Stuff) cointainingStuff.getCloned();

            hold(cointainingStuff,clonedCointainingStuff);


        }

    }

    private void hold(Stuff cointainingStuff, Stuff cloned) {

        locate(cointainingStuff,cloned);

    }


    @Override
    public void giveVelocity(Doodle doodle , Doodle cloned) {

        Velocity velocityToBeGiven = new Velocity(((Platform)(thisClonedAsset)).velocityToBeGiven);

        doodle.takeVelocityY(velocityToBeGiven, (Element) this, (Element) thisClonedAsset);

    }

    @Override
    public void locate(Stuff stuff, Stuff cloned) {

        Platform cld = (Platform) thisClonedAsset;

        stuff.beLocated(cld.getX(),cld.getY(),cld.getWidth(),cld.getHeight(), (CanLocate) this, (CanLocate) thisClonedAsset);
    }


    @Override
    public void beHit(Hitter hitter, Hitter cloned) {

        if(hitter instanceof Doodle){
            giveVelocity((Doodle) hitter, (Doodle) cloned);
        }

    }

    @Override
    public void overFlowScreen() {

        Platform cld = (Platform) thisClonedAsset;

        if(cld.getX()  > cld.getRight()){

            setLocation(cld.getRight(),cld.getY());

            setVelocityX(-Math.abs(cld.getVelocityX()));

        }else if(cld.getX() < -cld.getLeft()){

            setLocation(-cld.getLeft(),cld.getY());

            setVelocityX(Math.abs(cld.getVelocityX()));
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
