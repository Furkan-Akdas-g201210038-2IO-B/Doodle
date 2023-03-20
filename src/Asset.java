import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Asset {
    GamePanel gp;
    Rectangle mainRect;
    BufferedImage image;
    int defaultVelocityX;
    int defaultVelocityY;
    int velocityX;
    int velocityY;
    int a;
    int aWeakness;
    int aCounter;

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Rectangle getMainRect() {
        return mainRect;
    }

    public void setMainRect(Rectangle mainRect) {
        this.mainRect = mainRect;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getDefaultVelocityX() {
        return defaultVelocityX;
    }

    public void setDefaultVelocityX(int defaultVelocityX) {
        this.defaultVelocityX = defaultVelocityX;
    }

    public int getDefaultVelocityY() {
        return defaultVelocityY;
    }

    public void setDefaultVelocityY(int defaultVelocityY) {
        this.defaultVelocityY = defaultVelocityY;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getAWeakness() {
        return aWeakness;
    }

    public void setAWeakness(int aWeakness) {
        this.aWeakness = aWeakness;
    }

    public int getACounter() {
        return aCounter;
    }

    public void setACounter(int aCounter) {
        this.aCounter = aCounter;
    }

    public Asset(GamePanel gp, Rectangle mainRect, BufferedImage image, int defaultVelocityX, int defaultVelocityY,
                 int velocityX, int velocityY, int a, int aWeakness, int aCounter) {
        this.gp = gp;
        this.mainRect = mainRect;
        this.image = image;
        this.defaultVelocityX = defaultVelocityX;
        this.defaultVelocityY = defaultVelocityY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.a = a;
        this.aWeakness = aWeakness;
        this.aCounter = aCounter;
    }

    public void update(){

        int newX = mainRect.x + velocityX;
        int newY = mainRect.y + velocityY;

        mainRect.setLocation(newX,newY);

        aCounter++;

        if(aCounter%aWeakness==0){
            velocityY = velocityY + a;
            aCounter=0;
        }

        mainRect.setSize(mainRect.width,mainRect.height);

        setImage(image);


    }
    public void draw(Graphics2D g2){

        g2.drawImage(image, mainRect.x, mainRect.y, mainRect.width, mainRect.height, null);

    }


    /*private int spriteCounter=0;
    private int spriteTime=40;*/

}
