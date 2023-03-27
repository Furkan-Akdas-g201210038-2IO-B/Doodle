public class Force {


    private int aX=0;
    private int aY=1;

    private int aWeaknessX=1;
    private int aWeaknessY=8;

    private int aCounterX=0;
    private int aCounterY=0;

    Force(){}

    Force(Force force){
        cloneParToThis(force);
    }

    public void cloneParToThis(Force force){
        this.aX=force.aX;
        this.aY=force.aY;
        this.aWeaknessX=force.aWeaknessX;
        this.aWeaknessY=force.aWeaknessY;
        this.aCounterX=force.aCounterX;
        this.aCounterY=force.aCounterY;
    }

    public int getAX() {
        return aX;
    }

    public void setAX(int aX) {
        this.aX = aX;
    }

    public int getAY() {
        return aY;
    }

    public void setAY(int aY) {
        this.aY = aY;
    }

    public int getAWeaknessX() {
        return aWeaknessX;
    }

    public void setAWeaknessX(int aWeaknessX) {
        this.aWeaknessX = aWeaknessX;
    }

    public int getAWeaknessY() {
        return aWeaknessY;
    }

    public void setAWeaknessY(int aWeaknessY) {
        this.aWeaknessY = aWeaknessY;
    }

    public int getACounterX() {
        return aCounterX;
    }

    public void setACounterX(int aCounterX) {
        this.aCounterX = aCounterX;
    }

    public int getACounterY() {
        return aCounterY;
    }

    public void setACounterY(int aCounterY) {
        this.aCounterY = aCounterY;
    }
}
