package display.rectangle;

import assets.color.Color;
import display.Drawable;

import java.util.Arrays;

public class Rectangle implements Drawable {

    protected int[] p;
    protected Color color;
    protected int x;
    protected int y;
    protected int z;
    protected int w;
    protected int h;

    Rectangle(int x, int y, int z, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.color = color;
        this.p = new int[w * h];
        Arrays.fill(p, color.getValue());
    }

    @Override
    public int[] getP() {
        return new int[0];
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public int getW() {
        return 0;
    }

    @Override
    public int getH() {
        return 0;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public void setZ(int z) {

    }
}
