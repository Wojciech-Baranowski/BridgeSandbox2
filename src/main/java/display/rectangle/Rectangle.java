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
        return p;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setZ(int z) {
        this.z = z;
    }
}
