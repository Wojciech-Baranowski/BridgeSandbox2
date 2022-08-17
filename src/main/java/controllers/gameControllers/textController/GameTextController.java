package controllers.gameControllers.textController;

import controllers.gameControllers.backgroundController.GameBackgroundController;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.card.Color;

import static controllers.gameControllers.backgroundController.GameBackgroundController.getGameBackgroundController;
import static engine.display.DisplayBean.getDisplay;
import static gameLogic.game.Game.getGame;

public class GameTextController {

    private static GameTextController gameTextController;
    private final PlayerSymbols playerSymbols;
    private final Title title;
    private final Points points;
    private final Atu atu;
    private final CardNumber cardNumber;

    private GameTextController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable[] playerSymbolSlots = getGameBackgroundController().getPlayerSymbolSpace().getPlayerSymbolSlots();
        Drawable background = getGameBackgroundController().getBackground().getBackground();
        Drawable buttonsSpace = getGameBackgroundController().getButtonsSpace().getButtonsSpace();

        playerSymbols = new PlayerSymbols(drawableFactory, playerSymbolSlots);
        title = new Title(drawableFactory, background);
        points = new Points(drawableFactory, buttonsSpace);
        atu = new Atu(drawableFactory, buttonsSpace, getGame().getAtu());
        cardNumber = new CardNumber(drawableFactory, buttonsSpace);
    }

    public static GameTextController getGameTextController() {
        if (gameTextController == null) {
            gameTextController = new GameTextController();
        }
        return gameTextController;
    }

    public void updatePoints() {
        int[] points = getGame().getPoints();
        this.points.updatePoints(points);
    }

    public void updatePoints(int[] points) {
        this.points.updatePoints(points);
    }

    public void updateAtu() {
        Color atu = getGame().getAtu();
        GameBackgroundController backgroundController = getGameBackgroundController();
        this.atu.updateAtu(backgroundController.getBackground().getBackground(), atu);
    }

}
