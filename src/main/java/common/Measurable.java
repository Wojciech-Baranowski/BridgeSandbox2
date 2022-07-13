package common;

public interface Measurable {

    int getX();

    int getY();

    int getW();

    int getH();

    void setX(int x);

    void setY(int y);

    default boolean inBorders(int x, int y) {
        return (getX() <= x && x < getX() + getW()) && (getY() <= y && y < getY() + getH());
    }

}
