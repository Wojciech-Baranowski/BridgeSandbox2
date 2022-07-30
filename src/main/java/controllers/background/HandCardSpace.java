package controllers.background;

import engine.common.Visual;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class HandCardSpace {

    @Getter
    private final Drawable[] handCardSlots;

    HandCardSpace(DrawableFactory drawableFactory, Drawable table) {
        handCardSlots = new Drawable[PLAYER_NUMBER];

        handCardSlots[0] = drawableFactory.makeFramedRectangle(
                287 + table.getX(),
                10 + table.getY(),
                261,
                95,
                2,
                "darkGreen",
                "lightBlue");

        handCardSlots[1] = drawableFactory.makeFramedRectangle(
                564 + table.getX(),
                185 + table.getY(),
                261,
                95,
                2,
                "darkGreen",
                "lightBlue");

        handCardSlots[2] = drawableFactory.makeFramedRectangle(
                287 + table.getX(),
                360 + table.getY(),
                261,
                95,
                2,
                "darkGreen",
                "lightBlue");

        handCardSlots[3] = drawableFactory.makeFramedRectangle(
                10 + table.getX(),
                185 + table.getY(),
                261,
                95,
                2,
                "darkGreen",
                "lightBlue");

        for (Visual handCardPlace : handCardSlots) {
            getScene().addObjectHigherThan(handCardPlace, table);
        }
    }

}
