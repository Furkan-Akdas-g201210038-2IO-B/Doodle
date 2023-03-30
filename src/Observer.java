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


        doodle.clearCollidedAssets();

        int tempXDoodle=doodle.getSolidArea().x;
        int tempYDoodle=doodle.getSolidArea().y;

        doodle.getSolidArea().x =doodle.getSolidArea().x + doodle.getMainRect().x;
        doodle.getSolidArea().y =doodle.getSolidArea().y + doodle.getMainRect().y;

        for (Asset asset : assetManager.getElements()){

            int tempXPlatform = asset.getSolidArea().x;
            int tempYPlatform = asset.getSolidArea().y;

            asset.getSolidArea().x = asset.getMainRect().x + asset.getSolidArea().x;
            asset.getSolidArea().y = asset.getMainRect().y + asset.getSolidArea().y;

            if(doodle.getSolidArea().intersects(asset.getSolidArea())){

               doodle.addCollidedAsset(asset);
               //asset.addCollidedAsset(doodle);
            }

            asset.getSolidArea().x = tempXPlatform;
            asset.getSolidArea().y = tempYPlatform;

        }

         doodle.getSolidArea().x =tempXDoodle;
         doodle.getSolidArea().y =tempYDoodle;



    }


    public void x(){



    }

}
