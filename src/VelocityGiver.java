import java.awt.*;


public abstract class VelocityGiver extends Asset {

    Velocity velocityToBeGiven;

    VelocityGiver(VelocityGiver velocityGiver){
        super(velocityGiver);

        this.solidArea = (Rectangle) velocityGiver.solidArea.clone();

    }

    @Override
    public void cloneParToThis(Asset asset) {
        VelocityGiver velocityGiver = (VelocityGiver)asset;
        super.cloneParToThis(velocityGiver);
        this.velocityToBeGiven.cloneParToThis(velocityGiver.velocityToBeGiven);

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


    public abstract void giveVelocity(VelocityTaker velocityTaker,VelocityTaker cloned);


    @Override
    public String toString() {

        return super.toString() +
                "VelocityGiver{" +
                "velocityToBeGiven=" + velocityToBeGiven +
                '}';
    }
}
