import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Doodle extends Asset implements VelocityTakerFromAsset {

    private final BufferedImage rightImage;
    private final BufferedImage rightImage1;
    private final BufferedImage leftImage;
    private final BufferedImage leftImage1;

    private final int stopVelocity=0;
    private final int moveVelocity=5;

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

    }

    public boolean headingDown(){
        return getVelocityY() > 0;
    }

    public boolean headingUp(){
        return getVelocityY() <= 0;
    }

    @Override
    public void affect() {

        for(Asset collidedAsset : collidedAssets){

            if(collidedAsset instanceof CanBeActivated){
            activate((CanBeActivated) collidedAsset);
            }
        }

        clearCollidedAssets();

    }

    @Override
    public void overFlowScreen() {
        if(getX()  > getRight()){

            setLocation(getLeft(),getY());

        }else if(getX() <getLeft()){

            setLocation(getRight(),getY());
        }
    }

    public void activate(CanBeActivated canBeActivated) {
        if(headingDown()){
            System.out.println("ds");
            canBeActivated.beActivated();
        }
        System.out.println(getVelocityY());
    }

    /*
    @Override
    public void takeVelocity(Velocity givenVelocity) {
        cloneVelocityToThisVelocity(givenVelocity);
    }*/
    @Override
    public void takeVelocity(Velocity givenVelocity) {

        cloneVelocityToThisVelocity(givenVelocity);

    }
    @Override
    public void takeVelocityX(Velocity givenVelocityX) {
        setVelocityX(givenVelocityX.getX());
    }

    @Override
    public void takeVelocityY(Velocity givenVelocityY) {
        setVelocityY(givenVelocityY.getY());
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


    }



}
