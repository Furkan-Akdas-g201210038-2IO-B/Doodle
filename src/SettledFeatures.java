import java.awt.*;

public class SettledFeatures {

    Point location = new Point();
    Velocity velocity = new Velocity();

    private boolean isLocationSettled=false;
    private boolean isVelocityYSettled=false;
    private boolean isVelocityXSettled=false;

    public void setLocation(int x,int y){
        this.location.setLocation(x,y);
        isLocationSettled=true;
    }
    public int getX(){return this.location.x;}
    public int getY(){return this.location.y;}

    public int getVelocityX() {
        return velocity.getX();
    }
    public void setVelocityX(int velocityX) {
        velocity.setX(velocityX);
        isVelocityXSettled=true;
    }
    public int getVelocityY() {
        return velocity.getY();
    }
    public void setVelocityY(int velocityY) {
        velocity.setY(velocityY);
        isVelocityYSettled=true;
    }


    public boolean isLocationSettled() {
        return isLocationSettled;
    }

    public void setLocationSettled(boolean locationSettled) {
        isLocationSettled = locationSettled;
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
    }

    public void cloneVelocityToThisVelocity(Velocity velocity){

        setVelocityX(velocity.getX());
        setVelocityY(velocity.getY());

        isVelocityXSettled=true;

        isVelocityYSettled=true;
    }

    public Point getLocation() {
        return location;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void update(){

        isLocationSettled=false;

        isVelocityXSettled=false;

        isVelocityYSettled=false;


    }
}
