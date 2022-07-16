package display;

import assets.color.Color;

public interface Drawable extends Visual {

    int[] getP();

    int getX();

    int getY();

    int getW();

    int getH();

    void setX(int x);

    void setY(int y);

    default boolean inBorders(int x, int y) {
        return (getX() <= x && x < getX() + getW()) && (getY() <= y && y < getY() + getH());
    }

    default boolean isPixelOnPositionTransparent(int x, int y) {
        if (!inBorders(x, y))
            return true;
        int xPos = x - getX();
        int yPos = y - getY();
        return getP()[xPos + yPos * getW()] == Color.getTransparentColorValue();
    }

    default Drawable getDrawable() {
        return this;
    }

}
