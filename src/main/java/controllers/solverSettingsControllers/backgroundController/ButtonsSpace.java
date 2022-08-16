package controllers.solverSettingsControllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class ButtonsSpace {

    @Getter
    private final Drawable buttonsSpace;

    ButtonsSpace(DrawableFactory drawableFactory, Drawable background) {
        buttonsSpace = drawableFactory.makeFramedRectangle(
                background.getX(),
                background.getY(),
                600,
                240,
                2,
                "transparent",
                "lightBlue");
        getScene().addObjectHigherThan(buttonsSpace, background);
    }

}
