package controllers.probabilityModeControllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

@Getter
public class CardSlots {

    private final Drawable[] cardSlots;

    CardSlots(DrawableFactory drawableFactory, Drawable table) {
        cardSlots = new Drawable[PLAYER_NUMBER + 1];
        int[] x = {281, 553, 281, 9, 250};
        int[] y = {39, 170, 334, 170, 455};

        for (int i = 0; i < PLAYER_NUMBER + 1; i++) {
            cardSlots[i] = drawableFactory.makeFramedRectangle(
                    x[i] + table.getX(),
                    y[i] + table.getY(),
                    273,
                    95,
                    2,
                    "darkGreen",
                    "lightBlue");
            getScene().addObjectHigherThan(cardSlots[i], table);
        }
    }

}
