import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spring extends VelocityGiver implements CanBeActivated, CanBeLocatedOnAsset {

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


    void giveVelocity(VelocityTakerFromAsset velocityTakerFromAsset) {

        if(canGiveVelocity){

            Velocity velocityToBeGiven = new Velocity(this.velocityToBeGiven);

            if(velocityTakerFromAsset instanceof  Asset){
                velocityToBeGiven.setX(((Asset) velocityTakerFromAsset).getVelocityX());
            }

            if(velocityTakerFromAsset instanceof Doodle){
                if(((Doodle) velocityTakerFromAsset).headingDown()){
                    velocityTakerFromAsset.takeVelocity(velocityToBeGiven);
                    canGiveVelocity=false;
                }

            }
        }
    }

    @Override
    public void affect() {

        for (Asset collidedAsset : collidedAssets){

            if(collidedAsset instanceof VelocityTakerFromAsset){

                giveVelocity((VelocityTakerFromAsset) collidedAsset);

            }

        }

        clearCollidedAssets();

    }

    @Override
    public void overFlowScreen() {

    }

    @Override
    public void beActivated() {
        /*
        for (Asset collidedAsset : collidedAssets){
            if(collidedAsset instanceof Doodle){
                if(((Doodle) collidedAsset).headingDown()){
                    setImage(unComp);

                }

            }
        }*/

        setImage(unComp);
    }


    /*
    @Override
    public void beLocated(int x, int y,int width,int height) {
        setLocation(x + width/2,y - getHeight());
    }*/


    @Override
    public void beLocated(int x, int y, int width, int height) {
       setLocation(x + width/2,y - getHeight());
    }

    @Override
    public void beLocatedX(int x, int y, int width, int height) {
       // setLocation(x + width/2,y - getHeight());
    }

    @Override
    public void beLocatedY(int x, int y, int width, int height) {
        //setLocation(x + width/2,y - getHeight());
    }

    Spring(GamePanel gp){

        setGp(gp);

        setVelocityYToBeGiven(-10);

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
