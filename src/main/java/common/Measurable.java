package common;

public interface Measurable extends Comparable<Integer> {

    int getX();

    int getY();

    int getZ();

    int getW();

    int getH();

    void setX(int x);

    void setY(int y);

    void setZ(int z);

    @Override
    default int compareTo(Integer z) {
        return Integer.compare(getZ(), z);
    }

    default boolean inBorders(int x, int y) {
        return (getX() <= x && x < getX() + getW()) && (getY() <= y && y < getY() + getH());
    }

}
