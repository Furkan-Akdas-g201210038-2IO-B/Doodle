import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spring extends Stuff{


    private final BufferedImage comp;
    private final BufferedImage unComp;

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

        setVelocityYToBeGiven(-12);

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
    }


    @Override
    public void update() {
        super.update();
    }



    @Override
    public void startInteraction() {


        if(connectedToDoodle){

            Asset otherAsset = doodle;
            Asset otherClonedAsset = otherAsset.getCloned();

            interactWithDoodle(otherAsset,otherClonedAsset);

        }

    }

     public void interactWithDoodle(Asset otherAsset, Asset otherClonedAsset) {
        giveVelocity((Doodle) otherAsset, (Doodle) otherClonedAsset);

        ((Doodle)otherAsset).locate((Stuff)this, (Stuff) thisClonedAsset);
    }


    @Override
    public void giveVelocity(Doodle doodle,Doodle cloned) {

        Spring cln = (Spring) thisClonedAsset;

        if(cln.canGiveVelocity){

            Velocity velocityToBeGiven = new Velocity(((Spring)(thisClonedAsset)).velocityToBeGiven);

                if(((Doodle) cloned).headingDown())
                    doodle.takeVelocityY(cln.velocityToBeGiven, (Element) this, (Element) thisClonedAsset);

        }

    }

    @Override
    public void beHit(Hitter hitter, Hitter cloned) {

       if(hitter instanceof Doodle){
           setImage(unComp);

           isThisSolid=false;
           canGiveVelocity=false;
           connectedToDoodle=false;

           giveVelocity((Doodle) hitter, (Doodle) cloned);

       }


    }

    @Override
    public void beLocated(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {

        setLocation(x + width/2,y - getHeight());
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
