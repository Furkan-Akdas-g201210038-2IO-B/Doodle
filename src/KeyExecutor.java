public class KeyExecutor {

    GamePanel gp;

    private Doodle doodle;

    private KeyHandler keyHandler;


    KeyExecutor(GamePanel gp){
        this.gp=gp;
        keyHandler=this.gp.keyHandler;
        doodle = this.gp.assetManager.getDoodle();

    }

    public void execute(){


        if(keyHandler.isRightPressed())
            doodle.executeWhenRightPressed();
        else if(keyHandler.isLeftPressed())
            doodle.executeWhenLeftPressed();
        else
            doodle.executeWhenNonePressed();



        /*if(!keyHandler.isRightPressed() && !keyHandler.isLeftPressed()){
            doodle.executeWhenRightAndLeftReleased();
        }*/

    }



}
