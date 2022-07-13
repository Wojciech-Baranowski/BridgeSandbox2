package display;

import common.Measurable;

public interface Drawable extends Measurable {

    int[] getP();

    int getX();

    int getY();

    int getW();

    int getH();

    void setX(int x);

    void setY(int y);

}
