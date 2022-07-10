package display.text;

import assets.color.Color;
import assets.font.Font;
import display.Drawable;
import lombok.Getter;
import lombok.Setter;

public class Text implements Drawable {

    private final Font font;
    private final Color color;
    @Setter private String text;
    @Getter private int[] p;
    @Getter @Setter private int x;
    @Getter @Setter private int y;
    @Getter @Setter private int z;
    @Getter private int w;
    @Getter private int h;


    Text(String text, int x, int y, int z, Font font, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.z = z;
        this.font = font;
        this.color = color;
    }

}
