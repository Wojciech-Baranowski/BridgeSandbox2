package assets.color;

import lombok.Getter;

public class ArgbColor implements Color {

    @Getter  private final int value;

    ArgbColor(int value) {
        this.value = value;
    }

    @Override
    public Color blend(Color color) {
        int colorValue = color.getValue();
        float alpha = ((colorValue & 0xFF000000) >>> 24) * (1.0f / 255.0f);
        int red = (int)((((colorValue & 0x00FF0000) >>> 16) * alpha) + (((value & 0x00FF0000) >>> 16) * (1.0 - alpha)));
        int green = (int)((((colorValue & 0x0000FF00) >>> 8) * alpha) + (((value & 0x0000FF00) >>> 8) * (1.0 - alpha)));
        int blue = (int)(((colorValue & 0x000000FF) * alpha) + ((value & 0x000000FF) * (1.0 - alpha)));
        return new ArgbColor(0xFF << 24 | red << 16 | green << 8 | blue);
    }

    @Override
    public boolean isTransparent() {
        return (value >>> 24) != 0xFF;
    }

}
