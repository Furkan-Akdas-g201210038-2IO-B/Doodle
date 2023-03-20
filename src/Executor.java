public class Executor {

    GamePanel gp;

    private Observer observer;
    private AssetManager assetManager;

    private KeyHandler keyHandler;

    Executor(GamePanel gp){
        this.gp=gp;
        this.keyHandler=this.gp.keyHandler;
        this.observer=this.gp.observer;
        this.assetManager = this.gp.assetManager;
    }

    public void execute(){

        if(gp.observer.earnedVelocity<0)
            assetManager.doodle.setVelocityY(gp.observer.earnedVelocity);



    }



}
