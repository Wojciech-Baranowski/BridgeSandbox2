package display.rectangle;

import assets.color.Color;

public class FramedRectangle extends Rectangle {

    private int frameThickness;
    private Color frameColor;

    FramedRectangle(int x, int y, int z, int w, int h, int frameThickness, Color color, Color frameColor) {
        super(x, y, z, w, h, color);
        this.frameColor = frameColor;
        this.frameThickness = frameThickness;

    }
}
