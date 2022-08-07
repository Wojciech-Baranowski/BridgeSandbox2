package controllers.gameControllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class ButtonsSpace {

    @Getter
    private final Drawable buttonsSpace;

    ButtonsSpace(DrawableFactory drawableFactory, Drawable background) {
        buttonsSpace = drawableFactory.makeFramedRectangle(
                833 + background.getX(),
                background.getY(),
                367,
                675,
                2,
                "transparent",
                "lightBlue");
        getScene().addObjectHigherThan(buttonsSpace, background);
    }

}
