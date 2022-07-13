package display.text;

import assets.Assets;
import assets.AssetsBean;
import assets.color.Color;
import assets.font.Font;

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
