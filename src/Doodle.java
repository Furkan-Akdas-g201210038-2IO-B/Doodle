import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Doodle extends Hitter implements CanLocate{

    private final BufferedImage rightImage;
    private final BufferedImage rightImage1;
    private final BufferedImage leftImage;
    private final BufferedImage leftImage1;

    private  int stopVelocity=0;
    private  int moveVelocity=5;

    private boolean takeVelocityYLock1=false;
    private double trampolineNum=0;

    private boolean isAffectedByTrampoline=false;


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

        this.isAffectedByTrampoline=doodle.isAffectedByTrampoline;
        this.trampolineNum=doodle.trampolineNum;

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

        if(headingLeft())
            setVelocityX(0);

        setAX(1);
        setAWeaknessX(6);

    }

    public void executeWhenLeftPressed(){

        if(headingRight())
            setVelocityX(0);

        setAX(-1);
        setAWeaknessX(6);
    }

    public void animation(){

        if(getAX() > 0){

           if(headingUp())
                image = rightImage1;
            else if(headingDown())
                image = rightImage;

        }
        else if(getAX() < 0){

            if(headingUp())
                image = leftImage1;
            else if(headingDown())
                image = leftImage;

        }
        else {

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
    }
    public void executeWhenNonePressed(){

        setAX(0);

    }

    public boolean headingRight(){
        return getVelocityX() > 0;
    }

    public boolean headingLeft(){
        return getVelocityX() < 0;
    }


    @Override
    public void update() {


        if(isAffectedByTrampoline)
            checkAfterTrampoline();

        takeVelocityYLock1=false;

        super.update();
    }

    private void checkAfterTrampoline() {

            if(trampolineNum>6.28 || trampolineNum<-6.28){
                isAffectedByTrampoline=false;
                trampolineNum=0;

            }
    }

    @Override
    public void draw(Graphics2D g2) {
        //super.draw(g2);

        animation();

        if(isAffectedByTrampoline){

            int screenX = getX() - getGp().screen.getX();
            int screenY = getY() - getGp().screen.getY();

            if(screenY + 300 > 0 && screenY - 300 < getGp().screen.getHeight()) {

                trampolineNum=trampolineNum-0.08;

                Graphics2D gg =(Graphics2D) g2.create();
                gg.rotate(trampolineNum, screenX + mainRect.width /2 , screenY + mainRect.height/2);
                gg.drawImage(image, screenX, screenY, getWidth(), getHeight(), null);
                gg.dispose();

            }


        }else {
            super.draw(g2);
        }
    }

    public void affectByTrampoline(){
        isAffectedByTrampoline = true;
    }


    @Override
    public void startInteraction() {

        for(Element collidedElement : collidedElements){

            Element otherElement = collidedElement;
            Element otherClonedAsset = (Element) otherElement.getCloned();

            if(otherClonedAsset.isThisSolid)
                hit(otherElement,otherClonedAsset);

        }
    }

    @Override
    public void hit(Element element, Element cloned) {

            if(((Doodle)thisClonedAsset).headingDown())
                element.beHit((Hitter) this, (Hitter) thisClonedAsset);

    }

    @Override
    public void locate(Stuff stuff, Stuff cloned) {
        stuff.beLocated(thisClonedAsset.getX(),thisClonedAsset.getY(),thisClonedAsset.getWidth(),thisClonedAsset.getHeight()
                ,(CanLocate) this, (CanLocate) thisClonedAsset);
    }

    public void takeVelocity(Velocity givenVelocity, Element velocityGiver, Element cloned) {

        cloneVelocityToThisVelocity(givenVelocity);
    }

    public void takeVelocityX(Velocity givenVelocityX, Element velocityGiver, Element cloned) {
        setVelocityX(givenVelocityX.getX());
    }

    public void takeVelocityY(Velocity givenVelocityY, Element velocityGiver, Element cloned) {

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

        Doodle  cld = (Doodle) thisClonedAsset;

        if(cld.getX()  > cld.getRight()){

            setLocation(cld.getLeft(),cld.getY());

        }else if(cld.getX() <cld.getLeft()){

            setLocation(cld.getRight(),cld.getY());
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
