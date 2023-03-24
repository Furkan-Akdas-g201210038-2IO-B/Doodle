import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Asset {
    GamePanel gp=null;
    Rectangle mainRect=new Rectangle();
    BufferedImage image=null;

    Velocity velocity=new Velocity();

    Asset affectedAsset;

    //public Asset(){}

    Asset(Asset asset){
        //Buraya Dikkat!!!!!!
        this.gp = asset.gp;

        this.mainRect = (Rectangle) asset.mainRect.clone();

        //Buraya Dikkat!!!!!!!!!!!!
        this.image=asset.image;

        //velocity i kolonla

        this.affectedAsset =asset.affectedAsset;
    }

    public Asset(){}

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

    public void setLocation(int x,int y){this.mainRect.setLocation(x,y);}

    public int getX(){return this.mainRect.x;}
    public int getY(){return this.mainRect.y;}

    public void setSize(int width,int height){this.mainRect.setSize(width,height);}

    public int getWidth(){return this.mainRect.width;}
    public int getHeight(){return this.mainRect.height;}

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getDefaultVelocityX() {
        return velocity.getDefaultVelocityX();
    }

    public void setDefaultVelocityX(int defaultVelocityX) {
        velocity.setDefaultVelocityX(defaultVelocityX);
    }

    public int getDefaultVelocityY() {
        return velocity.getDefaultVelocityY();
    }

    public void setDefaultVelocityY(int defaultVelocityY) {
        velocity.setDefaultVelocityY(defaultVelocityY);
    }

    public int getVelocityX() {
        return velocity.getVelocityX();
    }

    public void setVelocityX(int velocityX) {
        velocity.setVelocityX(velocityX);
    }

    public int getVelocityY() {
        return velocity.getVelocityY();
    }

    public void setVelocityY(int velocityY) {
        velocity.setVelocityY(velocityY);

    }

    public int getA() {
        return velocity.getA();
    }

    public void setA(int a) {
        velocity.setA(a);
    }

    public int getAWeakness() {
        return velocity.getAWeakness();
    }

    public void setAWeakness(int aWeakness) {
        velocity.setAWeakness(aWeakness);
    }

    public int getACounter() {
        return velocity.getACounter();
    }

    public void setACounter(int aCounter) {
        velocity.setACounter(aCounter);
    }

    public Asset getAffectedAsset() {
        return affectedAsset;
    }
    public void setAffectedAsset(Asset affectedAsset){
        this.affectedAsset = affectedAsset;
    }
    public boolean containsAffectedAsset(){
        return affectedAsset !=null;
    }

    public final void update(){

        int newX = mainRect.x + getVelocityX();
        int newY = mainRect.y + getVelocityY();

        mainRect.setLocation(newX,newY);

        setACounter(getACounter()+1);

        if(getACounter()%getAWeakness()==0){

            velocity.setVelocityY(getVelocityY()+ getA()); ;

            setACounter(0);
        }

        mainRect.setSize(mainRect.width,mainRect.height);

        setImage(image);


    }
    public final void draw(Graphics2D g2){

        g2.drawImage(image, mainRect.x, mainRect.y, mainRect.width, mainRect.height, null);

    }


    /*private int spriteCounter=0;
    private int spriteTime=40;*/

}
