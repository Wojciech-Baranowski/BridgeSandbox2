package controllers.background;

import engine.common.Visual;
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

        playedCardSlots[0] = drawableFactory.makeFramedRectangle(
                97 + tableCenter.getX(),
                9 + tableCenter.getY(),
                69,
                95,
                2,
                "darkGreen",
                "lightBlue");

        playedCardSlots[1] = drawableFactory.makeFramedRectangle(
                175 + tableCenter.getX(),
                62 + tableCenter.getY(),
                69,
                95,
                2,
                "darkGreen",
                "lightBlue");

        playedCardSlots[2] = drawableFactory.makeFramedRectangle(
                97 + tableCenter.getX(),
                113 + tableCenter.getY(),
                69,
                95,
                2,
                "darkGreen",
                "lightBlue");

        playedCardSlots[3] = drawableFactory.makeFramedRectangle(
                20 + tableCenter.getX(),
                63 + tableCenter.getY(),
                69,
                95,
                2,
                "darkGreen",
                "lightBlue");

        for (Visual handCardPlace : playedCardSlots) {
            getScene().addObjectHigherThan(handCardPlace, tableCenter);
        }
    }

}
