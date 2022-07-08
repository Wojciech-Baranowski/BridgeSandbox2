package display.image;

import display.Drawable;

import java.awt.image.BufferedImage;

public class Image implements Drawable {

    private int[] p;
    private int x;
    private int y;
    private int z;
    private int w;
    private int h;

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
