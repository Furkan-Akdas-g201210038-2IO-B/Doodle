public class Executor {

    GamePanel gp;

    private Observer observer;
    private AssetManager assetManager;
    Executor(GamePanel gp){
        this.gp=gp;
        this.observer=this.gp.observer;
        this.assetManager = this.gp.assetManager;
    }

    public void execute(){

        if(gp.observer.earnedVelocity<0)
            assetManager.doodle.updateVelocityY(gp.observer.earnedVelocity); ;

    }



}
