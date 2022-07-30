package engine.display;

import engine.assets.color.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
public class DrawableComposition implements Drawable {


    private final int[] p;
    @Setter
    private int x;
    @Setter
    private int y;
    private final int w;
    private final int h;

    public DrawableComposition(Drawable bottom, Drawable top) {
        x = min(top.getX(), bottom.getX());
        y = min(top.getY(), bottom.getY());
        w = max(top.getX() + top.getW(), bottom.getX() + bottom.getW()) - x;
        h = max(top.getY() + top.getH(), bottom.getY() + bottom.getH()) - y;
        p = new int[w * h];
        Arrays.fill(p, Color.getTransparentColorValue());
        mergePixels(bottom);
        mergePixels(top);
    }

    private void mergePixels(Drawable drawable) {
        int startX = drawable.getX() - x;
        int startY = drawable.getY() - y;
        int endX = startX + drawable.getW();
        int endY = startY + drawable.getH();
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                if(drawable.getP()[x - startX + (y - startY) * drawable.getW()] != Color.getTransparentColorValue()) {
                    p[x + y * w] = drawable.getP()[x - startX + (y - startY) * drawable.getW()];
                }
            }
        }
    }
}
