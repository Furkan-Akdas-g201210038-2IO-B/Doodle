import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.ConstructorProperties;
import java.io.IOException;

public class Doodle extends Asset {

    private  Rectangle foots;
    private final BufferedImage rightImage;
    private final BufferedImage rightImage1;
    private final BufferedImage leftImage;
    private final BufferedImage leftImage1;

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

    Doodle(Doodle doodle){
        super(doodle);
        this.foots=(Rectangle)doodle.foots.clone();
    }
    Doodle(GamePanel gp){

        setGp(gp);
        setDefaultVelocityX(5);
        super.setVelocityY(-2);;
        setA(1);

        setAWeakness(10);

        int height = 90;
        int width = 90;
        mainRect = new Rectangle(200,100,width,height);
        foots = new Rectangle((int)(width*0.27),(int)(height*0.86),(int)(width*0.45),(int)(width*0.13));

        //width 62 height 60

        //foots width 28 height 8 x 16 y 52
        //scale width 0.45 height 0.13 x 0.25 y 0.86

        //head -> x:16 y:15 width:29 height:25
        //scale -> x:0.25 y:0.25 width:0.46 height:0.41

    }

    public Rectangle getFoots() {
        return foots;
    }

    public void setFoots(Rectangle foots) {
        this.foots = foots;
    }

    @Override
    public void setVelocityY(int newVelocityY){

        if(headingDown()) {
            super.setVelocityY(newVelocityY);
        }
    }


    public boolean headingDown(){
        return getVelocityY() > 0;
    }

    public boolean headingUp(){
        return getVelocityY() <= 0;
    }

    public void beAffectedByAffectedObject(){

        if(affectedAsset instanceof Platform){

           // if(((Platform) affectedAsset).velocityYToBeGiven)

        }

    }

    public void executeWhenRightPressed(){

        setVelocityX(Math.abs(getDefaultVelocityX()));

        if(headingUp())
            image = rightImage1;
        else if(headingDown())
            image = rightImage;

    }

    public void executeWhenLeftPressed(){

        setVelocityX(- Math.abs(getDefaultVelocityX()));

        if(headingUp())
            image = leftImage1;
        else if(headingDown())
            image = leftImage;
    }

    public void executeWhenNonePressed(){
        setVelocityX(0);

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
