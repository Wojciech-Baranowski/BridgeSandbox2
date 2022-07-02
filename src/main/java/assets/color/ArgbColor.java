package assets.color;

public class ArgbColor implements Color {

    int value;

    ArgbColor(int value) {

    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public Color blend(Color color) {
        return null;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

}
