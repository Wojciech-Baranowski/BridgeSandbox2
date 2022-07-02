package common;

public interface Clickable extends Measurable{

    int getX();
    int getY();
    int getZ();
    int getW();
    int getH();
    void setX(int x);
    void setY(int y);
    void setZ(int z);
    void executeAction();

}
