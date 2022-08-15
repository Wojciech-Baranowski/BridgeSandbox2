package controllers.editGameControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.player.Player;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PlayerSymbols {

    private final Drawable[] playerSymbols;

    PlayerSymbols(DrawableFactory drawableFactory, Drawable[][] cells) {
        playerSymbols = new Drawable[PLAYER_NUMBER];
        int[] x = {16, 17, 15, 9};
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            playerSymbols[i] = drawableFactory.makeText(
                    Player.values()[i].getSymbolString(),
                    x[i] + cells[0][i].getX(),
                    30 + cells[0][i].getY(),
                    "HBE48",
                    "black");
            getScene().addObjectHigherThan(playerSymbols[i], cells[0][i]);
        }
    }

}
