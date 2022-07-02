package assets.color;

public interface Color {

    int getValue();
    Color blend(Color color);
    boolean isTransparent();

}
