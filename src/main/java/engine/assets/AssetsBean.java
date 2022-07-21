package engine.assets;

import engine.assets.color.Color;
import engine.assets.color.ColorFactory;
import engine.assets.font.Font;
import engine.assets.font.FontFactory;

import java.util.HashMap;
import java.util.Map;

public class AssetsBean implements Assets {

    private static AssetsBean assets;
    private final ColorFactory colorFactory;
    private final FontFactory fontFactory;
    private final Map<String, Color> colors;
    private final Map<String, Font> fonts;

    private AssetsBean() {
        colorFactory = new ColorFactory();
        fontFactory = new FontFactory();
        colors = new HashMap<>();
        fonts = new HashMap<>();
    }

    public static Assets getAssets() {
        if (assets == null) {
            assets = new AssetsBean();
        }
        return assets;
    }

    @Override
    public Color getColor(String name) {
        return colors.get(name);
    }

    @Override
    public void addColor(String name, int value) {
        Color color = colorFactory.makeArgbColor(value);
        colors.put(name, color);
    }

    @Override
    public Font getFont(String name) {
        return fonts.get(name);
    }

    @Override
    public void addFont(String name, String path, String symbols) {
        Font font = fontFactory.makeRasterFont(path, symbols);
        fonts.put(name, font);
    }
}
