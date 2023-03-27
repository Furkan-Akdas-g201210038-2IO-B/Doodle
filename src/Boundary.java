public class Boundary {

    private int left;
    private int right;

    private int up;
    private int down;

    public Boundary(int left, int right, int up, int down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    public Boundary(){}

    public int getLeft() {
        return left;
    }

    public void setLeft(int xLeft) {
        this.left = xLeft;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int xRight) {
        this.right = xRight;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int yUp) {
        this.up = yUp;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int yDown) {
        this.down = yDown;
    }
}
