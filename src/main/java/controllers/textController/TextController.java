package controllers.textController;

import controllers.backgroundController.BackgroundController;
import engine.display.DisplayBean;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.card.Color;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static engine.display.DisplayBean.getDisplay;
import static gameLogic.game.Game.getGame;

public class TextController {

    private static TextController textController;
    private final PlayerSymbols playerSymbols;
    private final Title title;
    private final Points points;
    private final Atu atu;
    private final CardNumber cardNumber;

    private TextController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable[] playerSymbolSlots = getBackgroundController().getPlayerSymbolSpace().getPlayerSymbolSlots();
        Drawable background = getBackgroundController().getBackground().getBackground();
        Drawable buttonsSpace = getBackgroundController().getButtonsSpace().getButtonsSpace();

        playerSymbols = new PlayerSymbols(drawableFactory, playerSymbolSlots);
        title = new Title(drawableFactory, background);
        points = new Points(drawableFactory, buttonsSpace);
        atu = new Atu(drawableFactory, buttonsSpace, getGame().getAtu());
        cardNumber = new CardNumber(drawableFactory, buttonsSpace);
    }

    public static TextController getTextController() {
        if (textController == null) {
            textController = new TextController();
        }
        return textController;
    }

    public void updatePoints() {
        int[] points = getGame().getPoints();
        this.points.updatePoints(points);
    }

    public void updateAtu() {
        Color atu = getGame().getAtu();
        BackgroundController backgroundController = getBackgroundController();
        this.atu.updateAtu(backgroundController.getBackground().getBackground(), atu);
    }

}
