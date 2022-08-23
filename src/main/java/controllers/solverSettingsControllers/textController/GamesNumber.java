package controllers.solverSettingsControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;

public class GamesNumber {

    private final Text gamesNumber;

    GamesNumber(DrawableFactory drawableFactory, Drawable background) {
        gamesNumber = drawableFactory.makeText("Number of games: 1",
                20 + background.getX(),
                85 + background.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(gamesNumber, background);
    }

    public void updateGamesNumber(int gamesNumber) {
        this.gamesNumber.setText("Number of games: " + gamesNumber);
    }

}
