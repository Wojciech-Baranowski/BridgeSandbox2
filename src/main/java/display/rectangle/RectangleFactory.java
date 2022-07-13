package display.rectangle;

import assets.Assets;
import assets.AssetsBean;
import assets.color.Color;

public class RectangleFactory {

    private final Assets assets;

    public RectangleFactory() {
        this.assets = AssetsBean.getAssets();
    }

    public Rectangle makeRectangle(int x, int y, int w, int h, String colorName) {
        Color color = assets.getColor(colorName);
        return new Rectangle(x, y, w, h, color);
    }

    public Rectangle makeFramedRectangle(int x, int y, int w, int h, int frameThickness,
                                         String colorName, String frameColorName) {
        Color color = assets.getColor(colorName);
        Color frameColor = assets.getColor(frameColorName);
        return new FramedRectangle(x, y, w, h, frameThickness, color, frameColor);

    }

}
