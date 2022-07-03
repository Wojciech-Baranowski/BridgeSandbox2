package assets;

import assets.color.Color;

public interface Assets {

    Color getColor(String name);
    void addColor(String name, int value);

}
