import java.util.Random;

public class WorldCreator {


    private GamePanel gp;

    private Random random = new Random();

    private int cursorY;
    private final int stepMarginY=125;
    private final int worldHeight =750;

    private final int differentPlatformNum=2;

    private final int springFrequencyWeakness=1;

//    private final int stepNum = worldHeight/stepMarginY;
    private final int stepNum = 17;

    public WorldCreator(GamePanel gp){

        this.gp=gp;
        cursorY=0;

        placeSteps();
        placeDoodle();

    }

    public void placeSteps(){

        for (int i=0;i<stepNum;i++){

            int randomPlatformNum = random.nextInt(differentPlatformNum);
            int randomSpringNum = random.nextInt(springFrequencyWeakness);


            Platform platform=null;

            if(randomPlatformNum % differentPlatformNum == 0){

                platform = gp.assetManager.createStillPlatform();

            }else if(randomPlatformNum % differentPlatformNum == 1){

                platform = gp.assetManager.createMoveblePlatform();

            }

            if(randomSpringNum % springFrequencyWeakness==0){
                platform.addConnectedAsset(gp.assetManager.createSpring());
            }

            int randomX = random.nextInt(gp.gpWidth - platform.getWidth());

            platform.getSettledFeatures().setLocation(randomX,cursorY);

            cursorY+= stepMarginY;
        }

    }

    public void placeDoodle(){
        Doodle doodle = gp.assetManager.createDoodle();
        Asset lastStep = gp.assetManager.getSteps().get(gp.assetManager.getSteps().size() - 1);
        doodle.settledFeatures.setLocation(lastStep.settledFeatures.getX(),lastStep.settledFeatures.getY() - doodle.getHeight() - 30);
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}
