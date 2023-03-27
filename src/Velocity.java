public class Velocity {

    private int x =0;
    private int y =0;

    Velocity(){}

    Velocity(Velocity velocity){
        cloneParToThis(velocity);
    }

    public void cloneParToThis(Velocity velocity){
        this.x = velocity.x;
        this.y =velocity.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int velocityX) {
        this.x = velocityX;
    }

    public int getY() {
        return y;
    }

    public void setY(int velocityY) {
        this.y = velocityY;
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "velocityX=" + x +
                ", velocityY=" + y +
                '}';
    }
}
