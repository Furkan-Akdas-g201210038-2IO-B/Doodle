import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Element extends Asset {

    int velocityYToBeGiven=0;
    int aToBeGiven=0;
    Rectangle solidArea = new Rectangle();

    Element(Element element){
        super(element);
        this.velocityYToBeGiven=element.velocityYToBeGiven;
        this.aToBeGiven=element.aToBeGiven;
        this.solidArea = (Rectangle) element.solidArea.clone();
    }
    public Element(){}

    public int getVelocityYToBeGiven() {
        return velocityYToBeGiven;
    }

    public int getAToBeGiven() {
        return aToBeGiven;
    }

    public void setVelocityYToBeGiven(int velocityYToBeGiven) {
        this.velocityYToBeGiven = velocityYToBeGiven;
    }

    public void setAToBeGiven(int aToBeGiven) {
        this.aToBeGiven = aToBeGiven;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }




}
