package controllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PlayedCardSpace {

    @Getter
    private final Drawable[] playedCardSlots;

    PlayedCardSpace(DrawableFactory drawableFactory, Drawable tableCenter) {
        playedCardSlots = new Drawable[PLAYER_NUMBER];
        int[] x = {92, 170, 92, 14};
        int[] y = {14, 72, 130, 72};

        for (int i = 0; i < PLAYER_NUMBER; i++) {
            playedCardSlots[i] = drawableFactory.makeFramedRectangle(
                    x[i] + tableCenter.getX(),
                    y[i] + tableCenter.getY(),
                    69,
                    95,
                    2,
                    "darkGreen",
                    "lightBlue");
            getScene().addObjectHigherThan(playedCardSlots[i], tableCenter);
        }
    }

}
