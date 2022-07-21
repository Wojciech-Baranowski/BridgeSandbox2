package engine.assets;

import engine.assets.color.Color;
import engine.assets.font.Font;

public interface Assets {

    Color getColor(String name);

    void addColor(String name, int value);

    Font getFont(String name);

    void addFont(String name, String path, String symbols);

}
