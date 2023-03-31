import java.util.ArrayList;

public abstract class Hitter extends Asset {


    ArrayList<Element> collidedElements = new ArrayList<>();

    Hitter(){}

    @Override
    public void cloneParToThis(Asset asset) {
        super.cloneParToThis(asset);
        Hitter hitter = (Hitter) asset;
        this.collidedElements =hitter.collidedElements;
    }

    public void removeCollidedElement(Element element){
        collidedElements.remove(element);}
    public void clearCollidedElements(){
        collidedElements.clear();}
    public void addCollidedElement(Element element){
        collidedElements.add(element);
    }

    public abstract void hit(Element element,Element cloned);

}
