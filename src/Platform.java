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

       setVelocityYToBeGiven(-4);


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
        //setVelocityX(moveVelocityX);
    }

    /*
    public void moveAlongX(){
        //setVelocityX(moveVelocityX);
    }*/

    public void giveLocation(CanBeLocated canBeLocated){
        canBeLocated.beLocated(getX(),getY(),getWidth(),getHeight());

    }
    void giveVelocity(VelocityTaker velocityTaker) {

        Velocity velocityToBeGiven = new Velocity(this.velocityToBeGiven);

        if(velocityTaker instanceof Doodle){
            if(((Doodle) velocityTaker).headingDown())
                 velocityTaker.takeVelocity(velocityToBeGiven);
        }
    }

    @Override
    public void affect() {

        for (Asset collidedAsset : collidedAssets){

            if(collidedAsset instanceof VelocityTaker){

                giveVelocity((VelocityTaker) collidedAsset);

            }

        }

        for (Asset connectedAsset : connectedAssets){

            if(connectedAsset instanceof CanBeLocated){
                giveLocation((CanBeLocated) connectedAsset);
            }

        }

        clearCollidedAssets();
    }

    @Override
    public void update() {
        super.update();

        if(mainRect.x  > boundary.getRight()){

            mainRect.setLocation(boundary.getRight(),mainRect.y);

            setVelocityX(-Math.abs(getVelocityX()));

        }else if(mainRect.x < boundary.getLeft()){

            mainRect.setLocation(boundary.getLeft(),mainRect.y);

            setVelocityX(Math.abs(getVelocityX()));
        }
    }

    @Override
    public void beActivated() {
        if(solidness<0)
            return;

       setSolidness(getSolidness() - 1);

       if(solidness==0){
           setAY(1);
           setAWeaknessY(10);
           setACounterY(0);
       }
    }
}
