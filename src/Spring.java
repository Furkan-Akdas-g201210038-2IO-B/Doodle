import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spring extends VelocityGiver implements CanBeActivated,CanBeLocated {

    private final BufferedImage comp;
    private final BufferedImage unComp;

    private boolean canGiveVelocity=true;

    {
        try {
            comp = ImageIO.read(getClass().getResourceAsStream("/objects/spring_comp.png"));
            unComp = ImageIO.read(getClass().getResourceAsStream("/objects/spring.png"));

            image=comp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Spring (){}


    void giveVelocity(VelocityTaker velocityTaker) {

        if(canGiveVelocity){

            Velocity velocityToBeGiven = new Velocity(this.velocityToBeGiven);

            if(velocityTaker instanceof  Asset){
                velocityToBeGiven.setX(((Asset) velocityTaker).getVelocityX());
            }

            if(velocityTaker instanceof Doodle){
                if(((Doodle) velocityTaker).headingDown()){
                    velocityTaker.takeVelocity(velocityToBeGiven);
                    canGiveVelocity=false;
                }

            }
        }
    }

    @Override
    public void affect() {

        for (Asset collidedAsset : collidedAssets){

            if(collidedAsset instanceof VelocityTaker){

                giveVelocity((VelocityTaker) collidedAsset);

            }

        }

        clearCollidedAssets();

    }

    @Override
    public void beActivated() {

        for (Asset collidedAsset : collidedAssets){
            if(collidedAsset instanceof Doodle){
                if(((Doodle) collidedAsset).headingDown())
                    setImage(unComp);
            }
        }
    }


    /*
    @Override
    public void beLocated(int x, int y,int width,int height) {
        setLocation(x + width/2,y - getHeight());
    }*/


    @Override
    public void beLocated(int x, int y,int width,int height) {
       setLocation(x + width/2,y - getHeight());
    }

    Spring(GamePanel gp){

        setGp(gp);

        setVelocityYToBeGiven(-9);

        int height=30;
        int width=30;

        /*int height=22;
        int width=30;*/

        mainRect = new Rectangle(0,0,width,height);
        solidArea = new Rectangle(width*0,(int)(height*0.38),(int)(width*1),(int)(height*0.40));

        //size  -> width:21 height:44
        //solid -> x:0 y:17 width:21 height:18
        //scale -> x:0 y:0.38 width:1 height:0.40

    }


    @Override
    public void update() {
        super.update();
    }
}
