package engine.assets.font;

import engine.common.Rasterable;
import lombok.Getter;

public class RasterFontSymbol implements Rasterable {

    @Getter
    private final int[] p;
    @Getter
    private final int w;
    @Getter
    private final int h;

    RasterFontSymbol(int[] p, int w, int h) {
        this.p = p;
        this.w = w;
        this.h = h;
    }
}
