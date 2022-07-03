package assets.color;

public interface Color {

    int getValue();

    static int getTransparentColor(){
        return 0xFFFF00FF;
    }
    Color blend(Color color);
    boolean isTransparent();

}
