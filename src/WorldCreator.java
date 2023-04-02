import java.util.Random;

public class WorldCreator {


    private GamePanel gp;

    private Random random = new Random();

    private int cursorY;
    private final int stepMarginY=100;
    private final int worldHeight =1000;

    private final int platformNum = worldHeight/stepMarginY;

    public  int springFrequencyWeakness=9;
    public  int propellerFrequencyWeakness=6;
    public  int trampolineFrequencyWeakness=8;

    private final int stepNum = platformNum;

    public WorldCreator(GamePanel gp){

        this.gp=gp;
        cursorY=0;

        createPlatforms();
        connectStuffToPlatforms();
        placeSteps();
        placeDoodle();

    }

    private void createPlatforms() {
        for (int i=0;i<platformNum;i++){
            gp.assetManager.createStillPlatform();
        }
    }

    private void connectStuffToPlatforms() {

        for (Platform platform : gp.assetManager.getPlatforms()){


            int randomSpringNum = random.nextInt(springFrequencyWeakness);
            int randomPropellerNum = random.nextInt(propellerFrequencyWeakness);
            int randomTrampolineNum = random.nextInt(trampolineFrequencyWeakness);

            if(randomSpringNum % springFrequencyWeakness==0){
                Spring spring = gp.assetManager.createSpring();
                platform.setCointainingStuff(spring);
                spring.setPlatform(platform);
            }
            else if(randomPropellerNum % propellerFrequencyWeakness==0){
                Propeller propeller = gp.assetManager.createPropeller();
                platform.setCointainingStuff(propeller);
                propeller.setPlatform(platform);

            }else if(randomTrampolineNum % trampolineFrequencyWeakness==0){
                Trampoline trampoline = gp.assetManager.createTrampoline();
                platform.setCointainingStuff(trampoline);
                trampoline.setPlatform(platform);
            }


        }

    }

    public void placeSteps(){


        for (Platform platform : gp.assetManager.getPlatforms()){

            int randomX = random.nextInt(gp.gpWidth - platform.getWidth());

            platform.setLocation(randomX,cursorY);

            cursorY+= stepMarginY;
        }

    }

    public void placeDoodle(){
        Doodle doodle = gp.assetManager.createDoodle();
        Asset lastStep = gp.assetManager.getSteps().get(gp.assetManager.getSteps().size() - 1);
        doodle.setLocation(lastStep.getX(),lastStep.getY() - doodle.getHeight() - 30);
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}
