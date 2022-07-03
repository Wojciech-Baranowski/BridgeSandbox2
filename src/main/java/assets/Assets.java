package assets;

import assets.color.Color;
import assets.font.Font;

public interface Assets {

    Color getColor(String name);
    void addColor(String name, int value);
    Font getFont(String name);
    void addFont(String name, String path, char[] symbols);

}
