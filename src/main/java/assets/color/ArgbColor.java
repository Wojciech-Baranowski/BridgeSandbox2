package assets.color;

public class ArgbColor implements Color {

    int value;

    ArgbColor(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public Color blend(Color color) {
        float alpha = ((color.getValue() & 0xFF000000) >>> 24) * (1.0f / 255.0f);
        int red = (int)((((color.getValue() & 0x00FF0000) >>> 16) * alpha) + (((value & 0x00FF0000) >>> 16) * (1.0f - alpha)));
        int green = (int)((((color.getValue() & 0x0000FF00) >>> 8) * alpha) + (((value & 0x0000FF00) >>> 8) * (1.0f - alpha)));
        int blue = (int)(((color.getValue() & 0x000000FF) * alpha) + ((value & 0x000000FF) * (1.0f - alpha)));
        return new ArgbColor(0xFF << 24 | red << 16 | green << 8 | blue);
    }

    @Override
    public boolean isTransparent() {
        return (value >>> 24) == 255;
    }

}
