package controllers.solverSettingsControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static engine.scene.SceneBean.getScene;

public class Algorithms {

    private final Drawable algorithms;

    Algorithms(DrawableFactory drawableFactory, Drawable background) {
        algorithms = drawableFactory.makeText("Algorithms:",
                22 + background.getX(),
                260 + background.getY(),
                "HBE48",
                "black");
        getScene().addObjectHigherThan(algorithms, background);
    }
}
