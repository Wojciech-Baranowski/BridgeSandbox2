package controllers.backgroundController;

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
                92 + tableCenter.getX(),
                14 + tableCenter.getY(),
                69,
                95,
                2,
                "darkGreen",
                "lightBlue");

        playedCardSlots[1] = drawableFactory.makeFramedRectangle(
                170 + tableCenter.getX(),
                72 + tableCenter.getY(),
                69,
                95,
                2,
                "darkGreen",
                "lightBlue");

        playedCardSlots[2] = drawableFactory.makeFramedRectangle(
                92 + tableCenter.getX(),
                130 + tableCenter.getY(),
                69,
                95,
                2,
                "darkGreen",
                "lightBlue");

        playedCardSlots[3] = drawableFactory.makeFramedRectangle(
                14 + tableCenter.getX(),
                72 + tableCenter.getY(),
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
