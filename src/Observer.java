import java.util.ArrayList;

public class Observer {

    private GamePanel gp;
    private Doodle doodle;
    private ArrayList<Element> elements;

    //private Doodle clonedDoodle;
   // private ArrayList<Element> clonedElements=new ArrayList<>();
    public int earnedVelocity;
    public int earnedA;

    AssetManager assetManager;

    Observer(GamePanel gp){

        this.gp=gp;
        doodle = this.gp.assetManager.getDoodle();
        elements=this.gp.assetManager.getElements();
        assetManager=this.gp.assetManager;

    }



    public void observe(){

        earnedVelocity=0;
        earnedA=1;

        int tempXDoodle=doodle.getFoots().x;
        int tempYDoodle=doodle.getFoots().y;

        doodle.getFoots().x =doodle.getFoots().x + doodle.getMainRect().x;
        doodle.getFoots().y =doodle.getFoots().y + doodle.getMainRect().y;

        for (Element element : elements){

            int tempXPlatform = element.getSolidArea().x;
            int tempYPlatform = element.getSolidArea().y;

            element.getSolidArea().x = element.getMainRect().x + element.getSolidArea().x;
            element.getSolidArea().y = element.getMainRect().y + element.getSolidArea().y;

            if(doodle.getFoots().intersects(element.getSolidArea())){

                earnedVelocity = element.getVelocityYToBeGiven();
                earnedA = element.getAToBeGiven();

            }



            element.getSolidArea().x = tempXPlatform;
            element.getSolidArea().y = tempYPlatform;

        }

        doodle.getFoots().x =tempXDoodle;
        doodle.getFoots().y =tempYDoodle;



    }

}
