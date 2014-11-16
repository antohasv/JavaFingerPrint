/**
 * Created by anton on 12.11.14.
 */
class Minutia
{
    private int x;
    private int y;
    private int angle; // x - координата по оси x, y - по оси y,
    // angle - угол наклона минюции относительно оси x
    private boolean type;          // true - окончание, false - раздвоение

    // Конструктор класса
    public Minutia(int x, int y, int angle, boolean isEnd)
    {
        x = 0;
        y = 0;
        angle = 0;
    }

    public Minutia(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        Minutia minutia = (Minutia) obj;
        return this.x == minutia.getX() && this.y == minutia.getY();
    }

    public int getY() {
        return y;
    }

    public int getAngle() {
        return angle;
    }

    public boolean isType() {
        return type;
    }
}