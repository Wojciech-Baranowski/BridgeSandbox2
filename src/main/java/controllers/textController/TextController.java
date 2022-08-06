package controllers.textController;

import controllers.backgroundController.BackgroundController;
import engine.display.DisplayBean;
import engine.display.DrawableFactory;
import gameLogic.card.Color;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static gameLogic.game.Game.getGame;

public class TextController {

    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
    private static final BackgroundController backgroundController = getBackgroundController();
    private static TextController textController;
    private final PlayerSymbols playerSymbols;
    private final Points points;
    private final Atu atu;
    private final CardNumber cardNumber;

    private TextController() {
        playerSymbols =
                new PlayerSymbols(drawableFactory, backgroundController.getPlayerSymbolSpace().getPlayerSymbolSlots());
        points = new Points(drawableFactory, backgroundController.getButtonsSpace().getButtonsSpace());
        atu = new Atu(drawableFactory, backgroundController.getButtonsSpace().getButtonsSpace(), getGame().getAtu());
        cardNumber = new CardNumber(drawableFactory, backgroundController.getButtonsSpace().getButtonsSpace());
    }

    public static TextController getTextController() {
        if (textController == null) {
            textController = new TextController();
        }
        return textController;
    }

    public void updatePoints(int[] points) {
        this.points.updatePoints(points);
    }

    public void updateAtu(Color atu) {
        this.atu.updateAtu(backgroundController.getBackground().getBackground(), atu);
    }

}
