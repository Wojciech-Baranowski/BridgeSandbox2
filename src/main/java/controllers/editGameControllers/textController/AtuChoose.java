package controllers.editGameControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static engine.scene.SceneBean.getScene;

public class AtuChoose {

    private final Drawable atu;

    AtuChoose(DrawableFactory drawableFactory, Drawable background) {
        atu = drawableFactory.makeText("Atu:",
                120 + background.getX(),
                580 + background.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(atu, background);
    }

}
