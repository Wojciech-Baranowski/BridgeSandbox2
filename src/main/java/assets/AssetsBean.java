package assets;

import assets.color.Color;
import assets.color.ColorFactory;
import assets.font.Font;
import assets.font.FontFactory;

import java.util.HashMap;
import java.util.Map;

public class AssetsBean implements Assets{

    private static AssetsBean assets;
    private ColorFactory colorFactory;
    private FontFactory fontFactory;
    private Map<String, Color> colors;
    private Map<String, Font> fonts;

    private AssetsBean(){
        colorFactory = new ColorFactory();
        fontFactory = new FontFactory();
        colors = new HashMap<>();
        fonts = new HashMap<>();
    }

    public static Assets getAssets() {
        if(assets == null){
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
    public void addFont(String name, String path, char[] symbols) {
        Font font = fontFactory.makeRasterFont(path, symbols);
        fonts.put(name, font);
    }
}
