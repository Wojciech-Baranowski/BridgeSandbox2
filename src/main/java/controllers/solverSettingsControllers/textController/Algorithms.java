package controllers.solverSettingsControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static engine.scene.SceneBean.getScene;

public class Algorithms {

    private final Drawable algorithms;

    Algorithms(DrawableFactory drawableFactory, Drawable background) {
        algorithms = drawableFactory.makeText("Algorithms:",
                615 + background.getX(),
                15 + background.getY(),
                "HBE48",
                "black");
        getScene().addObjectHigherThan(algorithms, background);
    }
}
