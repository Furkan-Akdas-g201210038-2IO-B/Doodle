import java.awt.*;

public class AssetPart {


    private AssetPartName name;

    private Rectangle rectangle = new Rectangle();

    AssetPart(int x, int y, int width, int height, AssetPartName assetPartName){

        getRectangle().setLocation(x,y);
        getRectangle().setSize(width,height);
        setName(assetPartName);
    }

    public AssetPartName getName() {
        return name;
    }

    public void setName(AssetPartName name) {
        this.name = name;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
