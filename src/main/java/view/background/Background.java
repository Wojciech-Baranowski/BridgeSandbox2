package view.background;

import engine.common.Visual;
import engine.display.DisplayBean;
import engine.display.DrawableFactory;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class Background {
    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
    private Visual background;
    private Visual table;
    private Visual tableCenter;
    private Visual[] handCardPlaces;

    private Visual[] playedCardPlaces;

    public Background() {
        initializeBackground();
        initializeTable();
        initializeTableCenter();
        initializeHandCardPlaces();
        initializePlayedCardPlaces();
    }

    private void initializeBackground() {
        background = drawableFactory.makeFramedRectangle(0, 0, 1200, 675,
                2, "darkGray", "lightBlue");
        getScene().addOnHighest(background);
    }

    private void initializeTable() {
        table = drawableFactory.makeFramedRectangle(0, 35, 835, 465,
                2, "green", "lightBlue");
        getScene().addOnHighest(table);
    }

    private void initializeTableCenter() {
        tableCenter = drawableFactory.makeFramedRectangle(286, 158, 263, 217,
                2, "green", "lightBlue");
        getScene().addOnHighest(tableCenter);
    }

    private void initializeHandCardPlaces() {
        handCardPlaces = new Visual[PLAYER_NUMBER];
        handCardPlaces[0] = drawableFactory.makeFramedRectangle(287, 45, 261, 95,
                2, "darkGreen", "lightBlue");
        handCardPlaces[1] = drawableFactory.makeFramedRectangle(564, 220, 261, 95,
                2, "darkGreen", "lightBlue");
        handCardPlaces[2] = drawableFactory.makeFramedRectangle(287, 395, 261, 95,
                2, "darkGreen", "lightBlue");
        handCardPlaces[3] = drawableFactory.makeFramedRectangle(10, 220, 261, 95,
                2, "darkGreen", "lightBlue");

        for(Visual handCardPlace : handCardPlaces) {
            getScene().addOnHighest(handCardPlace);
        }
    }

    private void initializePlayedCardPlaces() {
        playedCardPlaces = new Visual[PLAYER_NUMBER];
        playedCardPlaces[0] = drawableFactory.makeFramedRectangle(383, 167, 69, 95,
                2, "darkGreen", "lightBlue");
        playedCardPlaces[1] = drawableFactory.makeFramedRectangle(461, 220, 69, 95,
                2, "darkGreen", "lightBlue");
        playedCardPlaces[2] = drawableFactory.makeFramedRectangle(383, 271, 69, 95,
                2, "darkGreen", "lightBlue");
        playedCardPlaces[3] = drawableFactory.makeFramedRectangle(306, 220, 69, 95,
                2, "darkGreen", "lightBlue");
        for(Visual handCardPlace : playedCardPlaces) {
            getScene().addOnHighest(handCardPlace);
        }
    }

}
