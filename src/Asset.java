import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Asset  {
    GamePanel gp=null;
    Rectangle mainRect=new Rectangle();
    BufferedImage image=null;
    Asset thisClonedAsset;

    final Velocity velocity=new Velocity();

    final Force force = new Force();

    Rectangle solidArea= new Rectangle();
    Boundary boundary=new Boundary();

    private boolean isLocationXSettled=false;
    private boolean isLocationYSettled=false;
    private boolean isVelocityYSettled=false;
    private boolean isVelocityXSettled=false;

    public Asset(){}

    Asset(Asset asset){
        //Buraya Dikkat!!!!!!
        this.gp = asset.gp;

        this.mainRect = (Rectangle) asset.mainRect.clone();

        //Buraya Dikkat!!!!!!!!!!!!
        this.image=asset.image;

        //velocity i kolonla

        //this.connectedAsset =asset.connectedAsset;
    }

    public void cloneParToThis(Asset asset){

        this.mainRect.setLocation(asset.getX(),asset.getY());
        this.mainRect.setSize(asset.getWidth(),asset.getHeight());
        this.velocity.cloneParToThis(asset.velocity);
        this.force.cloneParToThis(asset.force);
        this.boundary.cloneParToThis(asset.boundary);
        this.isLocationYSettled=asset.isLocationYSettled;
        this.isLocationXSettled=asset.isLocationXSettled;
        this.isVelocityYSettled=asset.isVelocityYSettled;
        this.isVelocityXSettled=asset.isVelocityXSettled;
        this.solidArea.setLocation(asset.solidArea.x,asset.solidArea.y);
        this.solidArea.setSize(asset.solidArea.width,asset.solidArea.height);

    }

    public Asset getCloned(){
       // thisClonedAsset.cloneParToThis(this);
        return  thisClonedAsset;
    }

    public void cloneClonedAsset(){
        thisClonedAsset.cloneParToThis(this);
    }

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
        setLocationXSettled(true);
        setLocationYSettled(true);
    }

    public void setLocationX(int x){
        this.mainRect.setLocation(x,getY());
        setLocationXSettled(true);
    }

    public void setLocationY(int y){
        this.mainRect.setLocation(getX(),y);
        setLocationYSettled(true);
    }

    public int getVelocityX() {
        return velocity.getX();
    }
    public void setVelocityX(int velocityX) {
        velocity.setX(velocityX);
        setVelocityXSettled(true);
    }
    public int getVelocityY() {
        return velocity.getY();
    }
    public void setVelocityY(int velocityY) {
        velocity.setY(velocityY);
        setVelocityYSettled(true);
    }

    public void setLocationForUpdate(int x,int y){
        this.mainRect.setLocation(x,y);
    }

    public void setLocationXForUpdate(int x){
        this.mainRect.setLocation(x,getY());
    }

    public void setLocationYForUpdate(int y){
        this.mainRect.setLocation(getX(),y);
    }

    public void setVelocityXForUpdate(int velocityX) {
        velocity.setX(velocityX);
    }
    public void setVelocityYForUpdate(int velocityY) {
        velocity.setY(velocityY);
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



    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public boolean isLocationXSettled() {
        return isLocationXSettled;
    }

    public void setLocationXSettled(boolean locationXSettled) {
        isLocationXSettled = locationXSettled;
    }

    public boolean isLocationYSettled() {
        return isLocationYSettled;
    }

    public void setLocationYSettled(boolean locationYSettled) {
        isLocationYSettled = locationYSettled;
    }

    public boolean isVelocityYSettled() {
        return isVelocityYSettled;
    }

    public void setVelocityYSettled(boolean velocityYSettled) {
        isVelocityYSettled = velocityYSettled;
    }

    public boolean isVelocityXSettled() {
        return isVelocityXSettled;
    }

    public void setVelocityXSettled(boolean velocityXSettled) {
        isVelocityXSettled = velocityXSettled;
        if(this instanceof Doodle) {

        }
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

    public abstract void overFlowScreen();

    public abstract void startInteraction();

    public  void update(){



        if(!(isLocationYSettled() || isVelocityYSettled())){

            setACounterY(getACounterY()+1);

            if(getACounterY()%getAWeaknessY()==0){

                setVelocityYForUpdate(getVelocityY()+ getAY()); ;

                setACounterY(0);
            }

        }



        if(!(isLocationXSettled() || isVelocityXSettled())){


            setACounterX(getACounterX()+1);

            if(getACounterX()%getAWeaknessX()==0){

                setVelocityXForUpdate(getVelocityX()+ getAX()); ;

                setACounterX(0);
            }

        }

        if(!isLocationXSettled()){
            int newX = getX() + getVelocityX();
            setLocationXForUpdate(newX);
        }

        if(!isLocationYSettled()){
            int newY = getY() + getVelocityY();
            setLocationYForUpdate(newY);
        }

        setSize(getWidth(),getHeight());

        setImage(image);

        setVelocityXSettled(false);
        setVelocityYSettled(false);
        setLocationXSettled(false);
        setLocationYSettled(false);

    }
    public  void draw(Graphics2D g2){

        int screenX = getX() - getGp().screen.getX();
        int screenY = getY() - getGp().screen.getY();


        if(screenY + 300 > 0 && screenY - 300 < getGp().screen.getHeight()){
            g2.drawImage(image, screenX, screenY, getWidth(), getHeight(), null);
        }

    }


    @Override
    public String toString() {
        return "Asset{" +
                ", mainRect=" + mainRect +
                ", velocity=" + velocity +
                ", force=" + force +
               /* ", connectedAssets=" + connectedAssets+
                ", collidedAssets=" + collidedAssets +*/
                ", solidArea=" + solidArea +
                ", boundary=" + boundary +
                ", isLocationXSettled=" + isLocationXSettled +
                ", isLocationYSettled=" + isLocationYSettled +
                ", isVelocityYSettled=" + isVelocityYSettled +
                ", isVelocityXSettled=" + isVelocityXSettled +
                '}';
    }
}
