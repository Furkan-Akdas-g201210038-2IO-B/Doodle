public abstract class Stuff extends Element {

    boolean connectedToDoodle =false;

    Platform platform;

    Doodle doodle;
    Stuff(){

    }
    @Override
    public void cloneParToThis(Asset asset) {

        super.cloneParToThis(asset);
        Stuff stuff = (Stuff) asset;
        this.connectedToDoodle =stuff.connectedToDoodle;
        this.doodle=stuff.doodle;
        this.platform=stuff.platform;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Doodle getDoodle() {
        return doodle;
    }

    public void setDoodle(Doodle doodle) {
        this.doodle = doodle;
    }

    public boolean isConnectedToDoodle() {
        return connectedToDoodle;
    }

    public void setConnectedToDoodle(boolean connectedToDoodle) {
        this.connectedToDoodle = connectedToDoodle;
    }

    public abstract void beLocated(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned);

    public abstract void beLocatedX(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned);

    public abstract void beLocatedY(int x, int y, int width, int height, CanLocate canLocate, CanLocate cloned);

}
