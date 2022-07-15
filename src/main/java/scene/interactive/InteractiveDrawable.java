package scene.interactive;

import display.Drawable;

public class InteractiveDrawable implements Drawable, Interactive {

    private Interactive parent;
    private Drawable drawable;

    public InteractiveDrawable(Drawable drawable, Interactive parent){
        this.drawable = drawable;
        this.parent = parent;
    }


    @Override
    public void update() {
        parent.update();
    }

    @Override
    public int[] getP() {
        return drawable.getP();
    }

    @Override
    public int getX() {
        return drawable.getX();
    }

    @Override
    public int getY() {
        return drawable.getY();
    }

    @Override
    public int getW() {
        return drawable.getW();
    }

    @Override
    public int getH() {
        return drawable.getH();
    }

    @Override
    public void setX(int x) {
        drawable.setX(x);
    }

    @Override
    public void setY(int y) {
        drawable.setY(y);
    }
}
