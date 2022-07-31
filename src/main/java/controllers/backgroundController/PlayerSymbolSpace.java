package controllers.backgroundController;

import engine.common.Visual;
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

        playerSymbolSlots[0] = drawableFactory.makeFramedRectangle(
                handCardSlots[0].getX() - 30,
                handCardSlots[0].getY() + 63,
                32,
                32,
                2,
                "blue",
                "lightBlue");

        playerSymbolSlots[1] = drawableFactory.makeFramedRectangle(
                handCardSlots[1].getX(),
                handCardSlots[1].getY() + 93,
                32,
                32,
                2,
                "violet",
                "lightBlue");

        playerSymbolSlots[2] = drawableFactory.makeFramedRectangle(
                handCardSlots[2].getX() - 30,
                handCardSlots[2].getY() + 63,
                32,
                32,
                2,
                "blue",
                "lightBlue");

        playerSymbolSlots[3] = drawableFactory.makeFramedRectangle(
                handCardSlots[3].getX(),
                handCardSlots[3].getY() + 93,
                32,
                32,
                2,
                "violet",
                "lightBlue");


        for (int i = 0; i < PLAYER_NUMBER; i++) {
            getScene().addObjectLowerThan(playerSymbolSlots[i], handCardSlots[i]);
        }
    }

}
