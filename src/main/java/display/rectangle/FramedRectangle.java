package display.rectangle;

import assets.color.Color;

public class FramedRectangle extends Rectangle {

    private int frameThickness;
    private Color frameColor;

    FramedRectangle(int x, int y, int z, int w, int h, int frameThickness, Color color, Color frameColor) {
        super(x, y, z, w, h, color);
        this.frameColor = frameColor;
        this.frameThickness = frameThickness;
        fillFrame();
        fillInside();
    }

    private void fillFrame() {
        for(int x = 0; x < w; x++){
            for(int y = 0; y < Math.min(frameThickness, h); y++){
                p[x + y * w] = frameColor.getValue();
            }
            for(int y = h - 1; y >= Math.max(h - frameThickness, 0); y--){
                p[x + y * w] = frameColor.getValue();
            }
        }
        for(int y = 0; y < h; y++){
            for(int x = 0; x < Math.min(frameThickness, w); x++){
                p[x + y * w] = frameColor.getValue();
            }
            for(int x = w - 1; x >= Math.max(w - frameThickness, 0); x--){
                p[x + y * w] = frameColor.getValue();
            }
        }
    }

    private void fillInside() {
        for(int x = frameThickness; x < w - frameThickness; x++){
            for(int y = frameThickness; y < h - frameThickness; y++){
                p[x + y * w] = color.getValue();
            }
        }
    }

}
