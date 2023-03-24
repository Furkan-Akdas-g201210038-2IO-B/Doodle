public class Velocity {


    private int defaultVelocityX=0;
    private int defaultVelocityY=0;
    private int velocityX=0;
    private int velocityY=0;
    private int a=0;
    private int aWeakness=1;
    private int aCounter=0;

    Velocity(){}

    Velocity(Velocity velocity){
        this.defaultVelocityX = velocity.defaultVelocityX;
        this.defaultVelocityY = velocity.defaultVelocityY;
        this.velocityX = velocity.velocityX;
        this.velocityY=velocity.velocityY;
        this.a=velocity.a;
        this.aWeakness=velocity.aWeakness;
        this.aCounter=velocity.aCounter;
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
}
