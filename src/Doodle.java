import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Doodle extends Asset {

    private final Rectangle foots;
    private final BufferedImage rightImage;
    private final BufferedImage rightImage1;
    private final BufferedImage leftImage;
    private final BufferedImage leftImage1;

    Doodle(GamePanel gp){
        super(gp,null,null,5,0,0,-8,1,10,0);
        int height = 90;
        int width = 90;
        mainRect = new Rectangle(200,300,width,height);
        foots = new Rectangle((int)(width*0.27),(int)(height*0.86),(int)(width*0.45),(int)(width*0.13));

        //width 62 height 60
        //foots width 28 height 8 x 16 y 52
        //scale width 0.45 height 0.13 x 0.25 y 0.86

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

    public Rectangle getFoots() {
        return foots;
    }


    @Override
    public void setVelocityY(int newVelocityY){

        if(headingDown()){
            velocityY=newVelocityY;
        }

    }

    public boolean headingDown(){
        return velocityY > 0;
    }

    public boolean headingUp(){
        return velocityY <= 0;
    }


    private void chooseImage(){

        if(gp.keyHandler.isRightPressed()){

            if(headingUp())
                image = rightImage1;
            else if(headingDown())
                image = rightImage;

        }else if(gp.keyHandler.isLeftPressed()){

            if(headingUp())
                image = leftImage1;
            else if(headingDown())
                image = leftImage;
        }else {

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

    public void executeWhenRightPressed(){

        velocityX = Math.abs(defaultVelocityX);

        if(headingUp())
            image = rightImage1;
        else if(headingDown())
            image = rightImage;

    }

    public void executeWhenLeftPressed(){

        velocityX = - Math.abs(defaultVelocityX);

        if(headingUp())
            image = leftImage1;
        else if(headingDown())
            image = leftImage;
    }

    public void executeWhenNonePressed(){
        velocityX=0;

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



    @Override
    public void update(){

        if(gp.keyHandler.isRightPressed()){

            velocityX = Math.abs(defaultVelocityX);
        }
        else if(gp.keyHandler.isLeftPressed()){
            velocityX = - Math.abs(defaultVelocityX);
        }
        else
            velocityX=0;


        int newX = mainRect.x + velocityX;
        int newY = mainRect.y + velocityY;

        mainRect.setLocation(newX,newY);

        aCounter++;

        if(aCounter%aWeakness==0){
            velocityY = velocityY + a;
            aCounter=0;
        }

        chooseImage();

    }

    @Override
    public void draw(Graphics2D g2) {

            g2.drawImage(image, mainRect.x, mainRect.y, mainRect.width, mainRect.height, null);

    }

}
