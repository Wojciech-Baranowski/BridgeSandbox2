package controllers.probabilityModeControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;

public class StartingPlayer {

    private final Text startingPlayer;

    StartingPlayer(DrawableFactory drawableFactory, Drawable background) {
        startingPlayer = drawableFactory.makeText(
                "Starting player:",
                14,
                620,
                "HBE32",
                "black");

        getScene().addObjectHigherThan(startingPlayer, background);
    }

}
