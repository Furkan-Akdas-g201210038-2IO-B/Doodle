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

    private int spriteCounter;
    private int spriteNum;

    private final int totalDistance=1000;


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

        int height=50;
        int width=50;



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
        this.spriteNum=propeller.spriteNum;
        this.spriteCounter=propeller.spriteCounter;

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

    private void checkAfterCollision(){

            Propeller cld = (Propeller) thisClonedAsset;
            if(cld.startDistance  - cld.getY()  > cld.totalDistance){

                if(doodle.getCloned().headingLeft()){
                    setVelocityX(-2);
                    setVelocityY(-5);
                    setLocation(cld.getX()-25,cld.getY());
                }else if(doodle.getCloned().headingRight()){
                    setVelocityX(+2);
                    setVelocityY(-5);
                    setLocation(cld.getX()+25,cld.getY());
                }else{

                }

                connectedToDoodle=false;
                doodle=null;
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

        Propeller cld = (Propeller) thisClonedAsset;

        if(canLocate instanceof Platform){
            setLocation(x +20,y - cld.getHeight());
        }

        if(canLocate instanceof Doodle){
            setLocation(x +20,y -5);
        }

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

            platform.setCointainingStuffToNull(this);
            platform=null;
        }


    }

    public void animation(){
        if(connectedToDoodle){

            spriteCounter++;
            if(spriteCounter > 6){
                if(spriteNum==0)
                    spriteNum=1;
                else if(spriteNum == 1)
                    spriteNum=2;
                else if(spriteNum == 2)
                    spriteNum=3;

                else if(spriteNum == 3)
                    spriteNum=1;


                spriteCounter=0;
            }


            if(spriteNum==0)
                setImage(propeller);
            else if(spriteNum==1)
                setImage(propeller1);
            else if(spriteNum==2)
                setImage(propeller2);
            else if(spriteNum==3)
                setImage(propeller3);

        }else{
            setImage(propeller);
        }
    }

    @Override
    public void update() {


        if(connectedToDoodle)
            checkAfterCollision();


        super.update();
    }


int x=-1;
    @Override
    public void draw(Graphics2D g2) {
        animation();
        super.draw(g2);


    }
}
