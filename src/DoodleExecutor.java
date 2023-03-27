public class DoodleExecutor {

    GamePanel gp;

    private Observer observer;
    private AssetManager assetManager;

    private KeyHandler keyHandler;

    DoodleExecutor(GamePanel gp){
        this.gp=gp;
        this.keyHandler=this.gp.keyHandler;
        this.observer=this.gp.observer;
        this.assetManager = this.gp.assetManager;
    }

    public void execute(){

        /*if(gp.observer.earnedVelocity<0){
            assetManager.doodle.setVelocityY(gp.observer.earnedVelocity);
            assetManager.doodle.setA(gp.observer.earnedA);
        }*/

        assetManager.doodle.affect();

        for (Asset element : assetManager.getElements())
            element.affect();


        if(keyHandler.isRightPressed())
            assetManager.doodle.executeWhenRightPressed();
        else if(keyHandler.isLeftPressed())
            assetManager.doodle.executeWhenLeftPressed();
        else
            assetManager.doodle.executeWhenNonePressed();


    }



}
