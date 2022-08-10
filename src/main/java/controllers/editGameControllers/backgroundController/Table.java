package controllers.editGameControllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import java.util.Arrays;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.COLOR_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class Table {

    @Getter
    private final Drawable[][] cells;

    Table(DrawableFactory drawableFactory, Drawable background) {
        cells = new Drawable[COLOR_NUMBER + 1][PLAYER_NUMBER];

        for(int i = 0; i < PLAYER_NUMBER; i++) {
            cells[0][i] = drawableFactory.makeFramedRectangle(
                    7 + background.getX(),
                    i * 101 + 35 + background.getY(),
                    62,
                    103,
                    2,
                    "gray",
                    "lightBlue");

            for(int j = 0; j < COLOR_NUMBER; j++) {
                cells[j + 1][i] = drawableFactory.makeFramedRectangle(
                        j * 281 + 67 + background.getX(),
                        i * 101 + 35 + background.getY(),
                        283,
                        103,
                        2,
                        "gray",
                        "lightBlue");
            }
        }


        for(Drawable cell : Arrays.stream(cells).flatMap(Arrays::stream).toList()) {
            getScene().addObjectHigherThan(cell, background);
        }
    }

}
