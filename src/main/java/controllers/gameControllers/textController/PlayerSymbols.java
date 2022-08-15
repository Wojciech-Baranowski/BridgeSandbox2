package controllers.gameControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PlayerSymbols {

    private final Text[] symbols;

    PlayerSymbols(DrawableFactory drawableFactory, Drawable[] playersSymbolSlots) {
        symbols = new Text[PLAYER_NUMBER];
        symbols[0] = drawableFactory.makeText(
                "N",
                playersSymbolSlots[0].getX() + 5,
                playersSymbolSlots[0].getY() + 2,
                "HBE32",
                "black");

        symbols[1] = drawableFactory.makeText(
                "E",
                playersSymbolSlots[1].getX() + 6,
                playersSymbolSlots[1].getY() + 2,
                "HBE32",
                "black");

        symbols[2] = drawableFactory.makeText(
                "S",
                playersSymbolSlots[2].getX() + 5,
                playersSymbolSlots[2].getY() + 2,
                "HBE32",
                "black");

        symbols[3] = drawableFactory.makeText(
                "W",
                playersSymbolSlots[3].getX() + 3,
                playersSymbolSlots[3].getY() + 2,
                "HBE32",
                "black");
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            getScene().addObjectHigherThan(symbols[i], playersSymbolSlots[i]);
        }
    }

}
