package engine.assets.color;

public interface Color {

    int getValue();

    Color blend(Color color);

    boolean isTransparent();

    static int getTransparentColorValue() {
        return 0xFFFF00FF;
    }

    static int getHoverMarkColorValue() {
        return 0x220000FF;
    }

    static boolean isTransparent(int value) {
        return (value >>> 24) != 0xFF;
    }

    static int blend(int value1, int value2) {
        if (value1 == Color.getTransparentColorValue() || value1 == 0) {
            return value2 | 0xFF000000;
        }
        float alpha = ((value2 & 0xFF000000) >>> 24) * (1.0f / 255.0f);
        int red = (int) ((((value2 & 0x00FF0000) >>> 16) * alpha) + (((value1 & 0x00FF0000) >>> 16) * (1.0 - alpha)));
        int green = (int) ((((value2 & 0x0000FF00) >>> 8) * alpha) + (((value1 & 0x0000FF00) >>> 8) * (1.0 - alpha)));
        int blue = (int) (((value2 & 0x000000FF) * alpha) + ((value1 & 0x000000FF) * (1.0 - alpha)));
        return 0xFF << 24 | red << 16 | green << 8 | blue;
    }

}
