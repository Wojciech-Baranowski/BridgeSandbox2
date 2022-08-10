package controllers.editGameControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static controllers.editGameControllers.backgroundController.EditGameBackgroundController.getEditGameBackgroundController;
import static engine.scene.SceneBean.getScene;

public class InvalidGameData {

    private final Drawable invalidGameData;

    InvalidGameData(DrawableFactory drawableFactory, Drawable background) {
        invalidGameData = drawableFactory.makeText("Invalid game data!",
                580 + background.getX(),
                580 + background.getY(),
                "HBE32",
                "red");
    }

    public void showText() {
        getScene().addOnHighest(invalidGameData);
    }

    public void hideText() {
        getScene().removeObject(invalidGameData);
    }

}
