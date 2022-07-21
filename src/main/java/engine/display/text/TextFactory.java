package engine.display.text;

import engine.assets.Assets;
import engine.assets.AssetsBean;
import engine.assets.color.Color;
import engine.assets.font.Font;

public class TextFactory {

    private final Assets assets;

    public TextFactory() {
        this.assets = AssetsBean.getAssets();
    }

    public Text makeText(String text, int x, int y, String fontName, String colorName) {
        Font font = assets.getFont(fontName);
        Color color = assets.getColor(colorName);
        return new Text(text, x, y, font, color);
    }

}
