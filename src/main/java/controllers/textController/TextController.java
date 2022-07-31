package controllers.textController;

import controllers.backgroundController.BackgroundController;
import engine.display.DisplayBean;
import engine.display.DrawableFactory;
import gameLogic.card.Color;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static gameLogic.game.Game.getGame;

public class TextController {

    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
    private static final BackgroundController getBackground = getBackgroundController();
    private static TextController textController;
    private final Points points;
    private final Atu atu;

    private TextController() {
        points = new Points(drawableFactory, getBackground.getBackground().getBackground());
        atu = new Atu(drawableFactory, getBackground.getBackground().getBackground(), getGame().getAtu());
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
        this.atu.updateAtu(getBackground.getBackground().getBackground(), atu);
    }

}
