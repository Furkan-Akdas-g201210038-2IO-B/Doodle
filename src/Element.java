public abstract class Element extends  Asset{

    boolean isThisSolid = true;

     boolean canGiveVelocity=true;
    Velocity velocityToBeGiven= new Velocity();
    Element(){}

    @Override
    public void cloneParToThis(Asset asset) {
        super.cloneParToThis(asset);
        Element element = (Element) asset;
        isThisSolid=element.isThisSolid;
        velocityToBeGiven.cloneParToThis(element.velocityToBeGiven);
        canGiveVelocity = element.canGiveVelocity;
    }

    public boolean isThisSolid() {
        return isThisSolid;
    }

    public void setThisSolid(boolean thisSolid) {
        isThisSolid = thisSolid;
    }

    public int getVelocityYToBeGiven() {
        return velocityToBeGiven.getY();
    }

    public void setVelocityYToBeGiven(int velocityYToBeGiven) {
        velocityToBeGiven.setY(velocityYToBeGiven);
    }

    public abstract void beHit(Hitter hitter,Hitter cloned);

    public abstract void giveVelocity(Doodle doodle,Doodle cloned);


}
