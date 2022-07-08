package display.image;

import display.Drawable;

import java.awt.image.BufferedImage;

public class Image implements Drawable {

    private final int[] p;
    private int x;
    private int y;
    private int z;
    private final int w;
    private final int h;

    Image(BufferedImage image, int x, int y, int z, int w, int h) {
        p = image.getRGB(0, 0, w, h, null, 0, w);
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
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
