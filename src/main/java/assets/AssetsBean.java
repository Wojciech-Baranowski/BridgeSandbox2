package assets;

import assets.color.ArgbColor;
import assets.color.Color;
import assets.color.ColorFactory;

import java.util.HashMap;
import java.util.Map;

public class AssetsBean implements Assets{

    private static AssetsBean assets;
    private ColorFactory colorFactory;
    private Map<String, Color> colors;

    private AssetsBean(){
        colorFactory = new ColorFactory();
        colors = new HashMap<>();
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
}
