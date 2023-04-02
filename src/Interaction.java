import java.util.Objects;

public class Interaction {

    private Asset asset1=null;
    private Asset asset2=null;
    private char type='H';

    public Interaction(){}

    public Interaction(Asset asset1, Asset asset2) {
        this.asset1 = asset1;
        this.asset2 = asset2;
    }

    public Interaction(Asset asset1, Asset asset2,char type) {
        this.asset1 = asset1;
        this.asset2 = asset2;
        this.type=type;
    }

    public boolean isThisEmpty(){
        return (asset1==null) || (asset2==null);
    }

    public Asset getAsset1() {
        return asset1;
    }

    public void setAsset1(Asset asset1) {
        this.asset1 = asset1;
    }

    public Asset getAsset2() {
        return asset2;
    }

    public void setAsset2(Asset asset2) {
        this.asset2 = asset2;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public boolean equals(Asset asset1, Asset asset2) {

        return (Objects.equals(this.asset1, asset1) && Objects.equals(this.asset2, asset2)) ||
                (Objects.equals(this.asset1, asset2) && Objects.equals(this.asset2, asset1)) ;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interaction that = (Interaction) o;
        return (Objects.equals(asset1, that.asset1) && Objects.equals(asset2, that.asset2)) ||
                (Objects.equals(asset1, that.asset2) && Objects.equals(asset2, that.asset1)) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(asset1, asset2);
    }
}
