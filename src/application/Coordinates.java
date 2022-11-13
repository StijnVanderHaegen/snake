package application;

public class Coordinates {
    public Coordinates() {
    }

    public Coordinates(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    private int x = 1;
    private int y = 1;

    public int getX() {
        return x / 25;
    }

    public int getRealX() {
        return x;
    }

    public void setX(int x) {
        this.x = x * 25;
    }

    public int getY() {
        return y / 25;
    }

    public int getRealY() {
        return y;
    }

    public void setY(int y) {
        this.y = y * 25;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;

        Coordinates that = (Coordinates) o;

        if (getX() != that.getX()) return false;
        return getY() == that.getY();
    }
}
