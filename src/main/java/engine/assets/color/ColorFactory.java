package engine.assets.color;

public class ColorFactory {

    public Color makeArgbColor(int value) {
        return new ArgbColor(value);
    }

}
