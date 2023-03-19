import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Doodle {

    private GamePanel gp;
    private final Rectangle screenRect;
    private final Rectangle foots;
    private final BufferedImage rightImage;
    private final BufferedImage rightImage1;
    private final BufferedImage leftImage;
    private final BufferedImage leftImage1;
    private BufferedImage image;

    public Rectangle getFoots() {
        return foots;
    }
    public Rectangle getScreenRect(){return screenRect;}

    /*private Rectangle cloneThisRectangle(Rectangle rectangle){
        return new Rectangle(rectangle.x, rectangle.y,rectangle.width,rectangle.height);
    }*/

    private final int defaultVelocityY=-5;

    private final int defaultVelocityX=5;
    private int velocityX = 0;
    private int velocityY = -8;
    private final int a = 1;
    private int aWeakness=6;
    private int aCounter=0;

    Doodle(GamePanel gp){
        this.gp=gp;
        int height = 90;
        int width = 90;
        screenRect = new Rectangle(200,300,width,height);
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

    public void updateVelocityY(int newVelocityY){

        if(headingDown()){
            velocityY=newVelocityY;
        }

    }

    public void update(){


        if(gp.keyHandler.isRightPressed()){

            velocityX = Math.abs(defaultVelocityX);
        }
        else if(gp.keyHandler.isLeftPressed()){
            velocityX = - Math.abs(defaultVelocityX);
        }
        else
            velocityX=0;

        int newX = screenRect.x + velocityX;
        int newY = screenRect.y + velocityY;

        screenRect.setLocation(newX,newY);

        aCounter++;

        if(aCounter%aWeakness==0){
            velocityY = velocityY + a;
            aCounter=0;
        }

        chooseImage();

    }

    public void draw(Graphics2D g2) {

            g2.drawImage(image, screenRect.x, screenRect.y, screenRect.width, screenRect.height, null);

    }

}
