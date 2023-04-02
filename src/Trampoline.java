import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Trampoline extends Stuff{


    private final BufferedImage comp;
    private final BufferedImage unComp;

    private boolean locatedLock1;

    {
        try {
            comp = ImageIO.read(getClass().getResourceAsStream("objects/trampoline/trampoline1.png"));
            unComp = ImageIO.read(getClass().getResourceAsStream("objects/trampoline/trampoline.png"));

            image=unComp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Trampoline(){

    }


    Trampoline(GamePanel gp){

        setGp(gp);

        setVelocityYToBeGiven(-15);

        int height=35;
        int width=45;

        /*int height=22;
        int width=30;*/

        mainRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle((int)(width*0.09),(int)(height*0.12),(int)(width*0.90),(int)(height*0.54));

        //size  -> width:41 height:24
        //solid -> x:4 y:3 width:37 height:13
        //scale -> x:0.09 y:0.12 width:0.90 height:0.54

        thisClonedAsset = new Trampoline();
    }

    @Override
    public Trampoline getCloned(){
        //thisClonedAsset.cloneParToThis(this);
        return (Trampoline) thisClonedAsset;
    }

    @Override
    public void cloneClonedAsset() {
        thisClonedAsset.cloneParToThis(this);
    }

    @Override
    public void cloneParToThis(Asset asset) {
        super.cloneParToThis(asset);
    }

    @Override
    public void startInteraction() {

        if(connectedToDoodle){

            Asset otherAsset = doodle;
            Asset otherClonedAsset = otherAsset.getCloned();

            interactWithDoodle(otherAsset,otherClonedAsset);

        }

    }

    @Override
    public void interactWithDoodle(Asset otherAsset, Asset otherClonedAsset) {

        giveVelocity((Doodle) otherAsset, (Doodle) otherClonedAsset);

        ((Doodle)otherAsset).locate((Stuff)this, (Stuff) thisClonedAsset);

    }
    @Override
    public void beHit(Hitter hitter, Hitter cloned) {

        if(hitter instanceof Doodle){
            setImage(comp);

            isThisSolid=false;
            canGiveVelocity=false;
            connectedToDoodle=false;

            giveVelocity((Doodle) hitter, (Doodle) cloned);
            ((Doodle) hitter).affectByTrampoline();
        }
    }

    @Override
    public void giveVelocity(Doodle doodle, Doodle cloned) {
        Trampoline cln = (Trampoline) thisClonedAsset;

        if(cln.canGiveVelocity){

            Velocity velocityToBeGiven = new Velocity(((Trampoline)(thisClonedAsset)).velocityToBeGiven);

            if(((Doodle) cloned).headingDown())
                doodle.takeVelocityY(cln.velocityToBeGiven, (Element) this, (Element) thisClonedAsset);

        }
    }

    @Override
    public void beLocated(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {
        setLocation(x + width/2 -10,y - getHeight() + 4);
    }

    @Override
    public void beLocatedX(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {

    }

    @Override
    public void beLocatedY(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned) {

    }



    @Override
    public void overFlowScreen() {

    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

    }
}
