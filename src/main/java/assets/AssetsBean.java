package assets;

import assets.color.Color;
import assets.color.ColorFactory;

import java.util.HashMap;
import java.util.Map;

public class AssetsBean implements Assets{

    private static AssetsBean assets;
    private ColorFactory colorFactory;
    private Map<String, Color> colors;

    private AssetsBean(){

    }

    public static Assets getAssets() {
        return null;
    }

    @Override
    public Color getColor(String name) {
        return null;
    }

    @Override
    public void addColor(String name, int value) {

    }
}
