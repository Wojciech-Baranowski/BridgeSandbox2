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

}
