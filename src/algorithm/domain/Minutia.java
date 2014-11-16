package algorithm.domain;

public class Minutia
{
    private int x;
    private int y;

    public Minutia(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Minutia minutia = (Minutia) obj;
        return this.x == minutia.getX() && this.y == minutia.getY();
    }
}