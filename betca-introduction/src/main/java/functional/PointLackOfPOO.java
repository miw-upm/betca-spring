package functional;

public class PointLackOfPOO {
    private int x;
    private int y;

    public PointLackOfPOO(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PointLackOfPOO() {
        this.x = 0;
        this.y = 0;
    }

    public PointLackOfPOO(int xy) {
        this.x = xy;
        this.y = xy;
    }

    public double module(int x, int y) {
        return Math.sqrt((double) x * x + y * y);
    }

    public double phase(int x, int y) {
        return Math.atan((double) y / x);
    }

    public void translateXOrigin(int x) {
        this.x -= x;
    }

    public void translateOrigin(int x, int y) {
        this.x -= x;
        this.y -= y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
