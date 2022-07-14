package display;

import assets.color.Color;
import common.Measurable;

public interface Drawable extends Measurable {

    int[] getP();

    int getX();

    int getY();

    int getW();

    int getH();

    void setX(int x);

    void setY(int y);

    default boolean isPixelOnPositionTransparent(int x, int y) {
        if (!inBorders(x, y))
            return true;
        int xPos = x - getX();
        int yPos = y - getY();
        return getP()[xPos + yPos * getW()] == Color.getTransparentColorValue();
    }

}
