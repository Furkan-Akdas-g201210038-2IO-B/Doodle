import java.util.ArrayList;

public abstract class Hitter extends Asset {


    ArrayList<Asset> collidedAssets = new ArrayList<>();

    Hitter(){}

    @Override
    public void cloneParToThis(Asset asset) {
        super.cloneParToThis(asset);
        Hitter hitter = (Hitter) asset;
        this.collidedAssets=hitter.collidedAssets;
    }

    public void removeCollidedAsset(Asset collidedAsset){collidedAssets.remove(collidedAsset);}
    public void clearCollidedAssets(){collidedAssets.clear();}
    public void addCollidedAsset(Asset collidedAsset){
        collidedAssets.add(collidedAsset);
    }

    public abstract void hit(Element element,Element cloned);

}
