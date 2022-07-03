package assets.font;

import common.Rasterable;

public class RasterFontSymbol implements Rasterable {

    private int[] p;
    private int w;
    private int h;

    RasterFontSymbol(int[] p, int w, int h) {
        this.p = p;
        this.w = w;
        this.h = h;
    }

    @Override
    public int[] getP() {
        return p;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }
}