package controllers.editGameControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.player.Player;

import static controllers.gameControllers.backgroundController.GameBackgroundController.getGameBackgroundController;
import static engine.scene.SceneBean.getScene;

public class StartingPlayerChoose {

    private final Drawable startingPlayer;

    StartingPlayerChoose(DrawableFactory drawableFactory, Drawable background) {
        startingPlayer = drawableFactory.makeText("Starting player:",
                20 + background.getX(),
                490 + background.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(startingPlayer, background);
    }

}
