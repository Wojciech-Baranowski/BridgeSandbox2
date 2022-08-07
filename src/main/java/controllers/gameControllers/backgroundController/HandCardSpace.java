package controllers.gameControllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.player.Player;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

@Getter
public class HandCardSpace {

    private final Drawable[] handCardSlots;

    HandCardSpace(DrawableFactory drawableFactory, Drawable table) {
        handCardSlots = new Drawable[PLAYER_NUMBER];
        int[] x = {281, 553, 281, 9};
        int[] y = {9, 185, 361, 185};

        for (int i = 0; i < PLAYER_NUMBER; i++) {
            handCardSlots[i] = drawableFactory.makeFramedRectangle(
                    x[i] + table.getX(),
                    y[i] + table.getY(),
                    273,
                    95,
                    2,
                    "darkGreen",
                    "lightBlue");
            getScene().addObjectHigherThan(handCardSlots[i], table);
        }
    }

}
