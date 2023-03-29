import java.awt.*;
import java.util.ArrayList;

public class Screen {

    private GamePanel gp;

    private Rectangle camera = new Rectangle();

    private Doodle doodle;

    private ArrayList<Asset> assets;
    public Boundary boundary;

    public Screen(GamePanel gp) {
        this.gp = gp;
        doodle =gp.assetManager.getDoodle();
        assets = gp.assetManager.getAssets();
        camera.setSize(gp.gpWidth,gp.gpHeight);
        camera.setLocation(0,gp.worldCreator.getWorldHeight() - getHeight());

        boundary = new Boundary(0,getWidth(),getY() + getHeight() /2,getY() + getHeight());

    }

    public void update(){

        if(doodle.getY() < boundary.getUp()){
            camera.setLocation(0,getY() + (doodle.getY() - boundary.getUp()));

            boundary.setUp(getY() + getHeight() / 2);
            boundary.setDown(getY() + getHeight());
        }


    }

    public int getX() {
        return camera.x;
    }

    public int getY() {
        return camera.y;
    }

    public int getWidth() {
        return camera.width;
    }

    public int getHeight() {
        return camera.height;
    }

    public void setLocation(int x, int y) {
        camera.setLocation(x, y);
    }

    public void setSize(int width, int height) {
        camera.setSize(width, height);
    }
}
