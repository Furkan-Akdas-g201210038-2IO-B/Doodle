import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Asset implements ForceTaker {
    GamePanel gp=null;
    Rectangle mainRect=new Rectangle();
    BufferedImage image=null;

    final Velocity velocity=new Velocity();

    final Force force = new Force();

    ArrayList<Asset> connectedAssets = new ArrayList<>();

    ArrayList<Asset> collidedAssets = new ArrayList<>();

    Boundary boundary;

    private boolean isLocationXSettled=false;
    private boolean isLocationYSettled=false;
    private boolean isVelocityYSettled=false;
    private boolean isVelocityXSettled=false;

    Asset(Asset asset){
        //Buraya Dikkat!!!!!!
        this.gp = asset.gp;

        this.mainRect = (Rectangle) asset.mainRect.clone();

        //Buraya Dikkat!!!!!!!!!!!!
        this.image=asset.image;

        //velocity i kolonla

        //this.connectedAsset =asset.connectedAsset;
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

    public void setLocation(int x,int y){
        this.mainRect.setLocation(x,y);

    }

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

    public int getVelocityX() {
        return velocity.getX();
    }
    public void setVelocityX(int velocityX) {
        velocity.setX(velocityX);
    }
    public int getVelocityY() {
        return velocity.getY();
    }
    public void setVelocityY(int velocityY) {
        velocity.setY(velocityY);
    }


    public int getAX() {
        return force.getAX();
    }

    public void setAX(int aX) {
        force.setAX(aX);
    }

    public int getAY() {
        return force.getAY();
    }

    public void setAY(int aY) {
        force.setAY(aY);
    }

    public int getAWeaknessX() {
        return force.getAWeaknessX();
    }

    public void setAWeaknessX(int aWeaknessX) {
        force.setAWeaknessX(aWeaknessX);
    }

    public int getAWeaknessY() {
        return force.getAWeaknessY();
    }

    public void setAWeaknessY(int aWeaknessY) {
        force.setAWeaknessY(aWeaknessY);
    }

    public int getACounterX() {
        return force.getACounterX();
    }

    public void setACounterX(int aCounterX) {
        force.setACounterX(aCounterX);
    }

    public int getACounterY() {
        return force.getACounterY();
    }

    public void setACounterY(int aCounterY) {
        force.setACounterY(aCounterY);
    }

    public void clearConnectedAssets(){connectedAssets.clear();}
    public void addConnectedAsset(Asset connectedAsset){
        connectedAssets.add(connectedAsset);
    }

    public void clearCollidedAssets(){collidedAssets.clear();}
    public void addCollidedAsset(Asset collidedAsset){
        collidedAssets.add(collidedAsset);
    }

    public int getLeft() {
        return boundary.getLeft();
    }

    public void setLeft(int xLeft) {
        boundary.setLeft(xLeft);
    }

    public int getRight() {
        return boundary.getRight();
    }

    public void setRight(int xRight) {
        boundary.setRight(xRight);
    }

    public int getUp() {
        return boundary.getUp();
    }

    public void setUp(int yUp) {
        boundary.setUp(yUp);
    }

    public int getDown() {
        return boundary.getDown();
    }

    public void setDown(int yDown) {
        boundary.setDown(yDown);
    }

    Rectangle solidArea;

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void cloneVelocityToThisVelocity(Velocity velocity){

        setVelocityX(velocity.getX());
        setVelocityY(velocity.getY());

    }

    public void cloneForceToThisForce(Force force){
        setAX(force.getAX());
        setAY(force.getAY());
        setAWeaknessX(force.getAWeaknessX());;
        setAWeaknessY(force.getAWeaknessY());;
        setACounterX(force.getACounterX());
        setACounterY(force.getACounterY());
    }

    @Override
    public void takeForce(Force force) {
        cloneForceToThisForce(force);
    }

    public abstract void affect();

    public  void update(){

        setACounterY(getACounterY()+1);

        if(getACounterY()%getAWeaknessY()==0){

            velocity.setY(getVelocityY()+ getAY()); ;

            setACounterY(0);
        }

        setACounterX(getACounterX()+1);

        if(getACounterX()%getAWeaknessX()==0){

            velocity.setX(getVelocityX()+ getAX()); ;

            setACounterX(0);
        }

/*
        if(changesBeforeUpdate.isVelocityXSettled()){
            setVelocityX(changesBeforeUpdate.getVelocityX());
        }

        if(changesBeforeUpdate.isVelocityYSettled()){
            setVelocityY(changesBeforeUpdate.getVelocityY());
        }*/


        int newX = getX() + getVelocityX();
        int newY = getY() + getVelocityY();

        setLocation(newX,newY);


       /* if(changesBeforeUpdate.isLocationSettled()){
            setLocation(changesBeforeUpdate.getX(), changesBeforeUpdate.getX());
        }*/


        setSize(getWidth(),getHeight());

        setImage(image);

    }
    public  void draw(Graphics2D g2){

        int screenX = getX() - getGp().screen.getX();
        int screenY = getY() - getGp().screen.getY();


        if(screenY + 300 > 0 && screenY - 300 < getGp().screen.getHeight()){
            g2.drawImage(image, screenX, screenY, mainRect.width, mainRect.height, null);
        }

    }

}
