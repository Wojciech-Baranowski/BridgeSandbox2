package display;

import assets.color.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class HoverMark implements Drawable {

    private static HoverMark hoverMark;
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

    private HoverMark() {}

    public static HoverMark getHoverMark() {
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
