package controllers.main.assets;

import engine.common.Visual;
import engine.display.Drawable;
import lombok.Getter;

import static engine.display.DisplayBean.getDisplay;

public class InactiveOverlay {

    @Getter
    private final Drawable drawable;

    public InactiveOverlay(Visual coveredObject) {
        Drawable coveredObjectDrawable = coveredObject.getDrawable();
        drawable = getDisplay().getDrawableFactory().makeRectangle(
                coveredObjectDrawable.getX(),
                coveredObjectDrawable.getY(),
                coveredObjectDrawable.getW(),
                coveredObjectDrawable.getH(),
                "transparentGray");
    }

    public void move(int x, int y) {
        drawable.setX(x);
        drawable.setY(y);
    }

}
