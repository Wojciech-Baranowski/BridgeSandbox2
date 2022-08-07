package controllers.gameControllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PlayerSymbolSpace {

    @Getter
    private final Drawable[] playerSymbolSlots;

    PlayerSymbolSpace(DrawableFactory drawableFactory, Drawable[] handCardSlots) {
        playerSymbolSlots = new Drawable[PLAYER_NUMBER];
        int[] x = {-30, 0, -30, 0};
        int[] y = {63, 93, 63, 93};

        for (int i = 0; i < PLAYER_NUMBER; i++) {
            playerSymbolSlots[i] = drawableFactory.makeFramedRectangle(
                    x[i] + handCardSlots[i].getX(),
                    y[i] + handCardSlots[i].getY(),
                    32,
                    32,
                    2,
                    (i % 2 == 0) ? "pink" : "violet",
                    "lightBlue");
            getScene().addObjectLowerThan(playerSymbolSlots[i], handCardSlots[i]);
        }
    }

}
