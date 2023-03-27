import java.awt.*;

public abstract class VelocityGiver extends Asset {

    Velocity velocityToBeGiven;

    VelocityGiver(VelocityGiver velocityGiver){
        super(velocityGiver);

        this.solidArea = (Rectangle) velocityGiver.solidArea.clone();

    }
    public VelocityGiver(){
        velocityToBeGiven= new Velocity();
    }
    public int getVelocityYToBeGiven() {
        return velocityToBeGiven.getY();
    }

    public void setVelocityYToBeGiven(int velocityYToBeGiven) {
        velocityToBeGiven.setY(velocityYToBeGiven); ;
    }


}
