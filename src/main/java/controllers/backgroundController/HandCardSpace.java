package controllers.backgroundController;

import engine.common.Visual;
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

        handCardSlots[0] = drawableFactory.makeFramedRectangle(
                281 + table.getX(),
                9 + table.getY(),
                273,
                95,
                2,
                "darkGreen",
                "lightBlue");

        handCardSlots[1] = drawableFactory.makeFramedRectangle(
                553 + table.getX(),
                185 + table.getY(),
                273,
                95,
                2,
                "darkGreen",
                "lightBlue");

        handCardSlots[2] = drawableFactory.makeFramedRectangle(
                281 + table.getX(),
                361 + table.getY(),
                273,
                95,
                2,
                "darkGreen",
                "lightBlue");

        handCardSlots[3] = drawableFactory.makeFramedRectangle(
                9 + table.getX(),
                185 + table.getY(),
                273,
                95,
                2,
                "darkGreen",
                "lightBlue");

        activeHandOverlay = drawableFactory.makeFramedRectangle(
                0,
                0,
                273,
                95,
                2,
                "transparent",
                "yellow");

        for (Visual handCardPlace : handCardSlots) {
            getScene().addObjectHigherThan(handCardPlace, table);
        }
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
