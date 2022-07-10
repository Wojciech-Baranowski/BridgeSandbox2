package assets;

import assets.color.Color;
import assets.font.Font;

public interface Assets {

    Color getColor(String name);
    void addColor(String name, int value);
    Font getFont(String name);
    void addFont(String name, String path, String symbols);

    public static String getExtendedAlphabet() {
        return "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u2664\u2665\u2666\u2667\u2468\u2469";
    }

}
