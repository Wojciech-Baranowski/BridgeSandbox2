package display.rectangle;

import assets.color.Color;
import display.Drawable;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class Rectangle implements Drawable {

    @Getter
    protected final int[] p;

    @Getter
    @Setter
    protected int x;
    @Getter
    @Setter
    protected int y;
    @Getter
    @Setter
    protected int z;
    @Getter
    protected final int w;
    @Getter
    protected final int h;
    protected final Color color;

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

}
