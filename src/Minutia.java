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

    public int getX() {
        return x;
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