import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Platform extends VelocityGiver implements CanBeActivated {

    Platform(Platform platform){
        super(platform);
    }
    Platform(){}

    private final int stopVelocity=0;
    private final int moveVelocityX=3;

    private int solidness=-1;
    Platform(GamePanel gp){

       setGp(gp);
       setAY(0);

       setVelocityYToBeGiven(-7);


       int height=23;
       int width=90;


       mainRect = new Rectangle(0,0,width,height);
       solidArea = new Rectangle(0,0,width,height/8);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/platforms/greenPlatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boundary = new Boundary(0,gp.gpWidth-getWidth(),-1,-1);

    }

    public void setSolidness(int solidness) {
        this.solidness = solidness;
    }

    public int getSolidness() {
        return solidness;
    }

    public void moveAlongX(){
        setVelocityX(moveVelocityX);
    }

    public void giveLocation(CanBeLocatedOnAsset canBeLocatedOnAsset){
        canBeLocatedOnAsset.beLocated(getX(),getY(),getWidth(),getHeight());

    }
    void giveVelocity(VelocityTakerFromAsset velocityTakerFromAsset) {

        Velocity velocityToBeGiven = new Velocity(this.velocityToBeGiven);

        /*
        if(velocityTaker instanceof  Asset){
            velocityToBeGiven.setX(((Asset) velocityTaker).getVelocityX());
        }*/

        if(velocityTakerFromAsset instanceof Doodle){
            if(((Doodle) velocityTakerFromAsset).headingDown())
                 velocityTakerFromAsset.takeVelocityY(velocityToBeGiven);
        }
    }

    @Override
    public void affect() {

        for (Asset collidedAsset : collidedAssets){

            if(collidedAsset instanceof VelocityTakerFromAsset){

                giveVelocity((VelocityTakerFromAsset) collidedAsset);

            }

        }

        for (Asset connectedAsset : connectedAssets){

            if(connectedAsset instanceof CanBeLocatedOnAsset){
                giveLocation((CanBeLocatedOnAsset) connectedAsset);
            }

        }

        clearCollidedAssets();
    }

    @Override
    public void overFlowScreen() {
        if(getX()  > getRight()){

            setLocation(getRight(),getY());

            setVelocityX(-Math.abs(getVelocityX()));

        }else if(getX() < -getLeft()){

            setLocation(-getLeft(),getY());

            setVelocityX(Math.abs(getVelocityX()));
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void beActivated() {
      /*dd
        if(solidness<0)
            return;

       setSolidness(getSolidness() - 1);

       if(solidness==0){
           setAY(1);
           setAWeaknessY(10);
           setACounterY(0);
       }

       */
    }
}
