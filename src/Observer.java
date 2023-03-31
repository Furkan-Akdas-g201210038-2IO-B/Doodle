public class Observer {

    private GamePanel gp;
    private Doodle doodle;

    AssetManager assetManager;

    Observer(GamePanel gp){

        this.gp=gp;
        doodle = this.gp.assetManager.getDoodle();
        assetManager=this.gp.assetManager;

    }

    public void observe(){


        doodle.clearCollidedElements();

        int tempXDoodle=doodle.getSolidArea().x;
        int tempYDoodle=doodle.getSolidArea().y;

        doodle.getSolidArea().x =doodle.getSolidArea().x + doodle.getMainRect().x;
        doodle.getSolidArea().y =doodle.getSolidArea().y + doodle.getMainRect().y;

        for (Element element : assetManager.getElements()){

            int tempXPlatform = element.getSolidArea().x;
            int tempYPlatform = element.getSolidArea().y;

            element.getSolidArea().x = element.getMainRect().x + element.getSolidArea().x;
            element.getSolidArea().y = element.getMainRect().y + element.getSolidArea().y;

            if(doodle.getSolidArea().intersects(element.getSolidArea())){

               doodle.addCollidedElement(element);
               //asset.addCollidedAsset(doodle);
            }

            element.getSolidArea().x = tempXPlatform;
            element.getSolidArea().y = tempYPlatform;

        }

         doodle.getSolidArea().x =tempXDoodle;
         doodle.getSolidArea().y =tempYDoodle;



    }


    public void x(){



    }

}
