package display;

import assets.color.Color;
import common.Visual;
import display.drawable.Drawable;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class HoverMark implements Drawable {

    private static Visual hoverMark;
    @Getter
    private int[] p;
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private int y;
    @Getter
    private int w;
    @Getter
    private int h;

    private HoverMark() {
        p = new int[1];
        p[0] = Color.getHoverMarkColorValue();
        x = y = 0;
        w = h = 1;
    }

    public static Visual getHoverMark() {
        if (hoverMark == null) {
            hoverMark = new HoverMark();
        }
        return hoverMark;
    }

    public void fitHoverMarkToDrawable(Drawable drawable) {
        x = drawable.getX();
        y = drawable.getY();
        w = drawable.getW();
        h = drawable.getH();
        p = new int[w * h];
        Arrays.fill(p, Color.getHoverMarkColorValue());
    }

}
