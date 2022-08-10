package controllers.editGameControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static engine.scene.SceneBean.getScene;

public class InvalidGameData {

    private final Drawable invalidGameData;

    InvalidGameData(DrawableFactory drawableFactory, Drawable background) {
        invalidGameData = drawableFactory.makeText("Invalid data!",
                1020 + background.getX(),
                494 + background.getY(),
                "HBE24",
                "red");
    }

    public void showText() {
        getScene().addOnHighest(invalidGameData);
    }

    public void hideText() {
        getScene().removeObject(invalidGameData);
    }

}
