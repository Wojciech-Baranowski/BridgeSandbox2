package controllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class ProgramTitle {

    @Getter
    private final Drawable title;

    ProgramTitle(DrawableFactory drawableFactory, Drawable background) {
        title = drawableFactory.makeText(
                "BridgeSandbox2 v1.0",
                background.getX() + 4,
                background.getY() + 4,
                "HBE32",
                "darkGray");
        getScene().addObjectHigherThan(title, background);
    }

}
