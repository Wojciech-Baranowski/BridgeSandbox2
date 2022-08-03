package controllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.player.Player;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

@Getter
public class HandCardSpace {

    private final Drawable[] handCardSlots;
    private final Drawable activeHandOverlay;

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

        activeHandOverlay = drawableFactory.makeFramedRectangle(
                0,
                0,
                273,
                95,
                2,
                "transparent",
                "yellow");
    }

    public void updateOverlay(Player player) {
        int x = handCardSlots[player.ordinal()].getX();
        int y = handCardSlots[player.ordinal()].getY();
        activeHandOverlay.setX(x);
        activeHandOverlay.setY(y);
        getScene().removeObject(activeHandOverlay);
        getScene().addObjectHigherThan(activeHandOverlay, handCardSlots[player.ordinal()]);
    }

}
