package controllers.gameControllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class Background {

    @Getter
    private final Drawable background;

    Background(DrawableFactory drawableFactory) {
        background = drawableFactory.makeFramedRectangle(
                0,
                0,
                1200,
                675,
                2,
                "darkGray",
                "lightBlue");
        getScene().addOnLowest(background);
    }

}
