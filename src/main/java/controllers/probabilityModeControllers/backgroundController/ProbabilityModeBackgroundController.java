package controllers.probabilityModeControllers.backgroundController;

import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.display.DisplayBean.getDisplay;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class ProbabilityModeBackgroundController {

    private static ProbabilityModeBackgroundController probabilityModeBackgroundController;
    @Getter
    private final Background background;
    private final Table table;
    @Getter
    private final PlayerSymbolSpace playerSymbolSpace;
    @Getter
    private CardSlots cardSlots;


    private ProbabilityModeBackgroundController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        background = new Background(drawableFactory);
        table = new Table(drawableFactory, background.getBackground());
        cardSlots = new CardSlots(drawableFactory, table.getTable());
        playerSymbolSpace = new PlayerSymbolSpace(drawableFactory, cardSlots.getCardSlots());
    }

    public static ProbabilityModeBackgroundController getProbabilityModeBackgroundController() {
        if (probabilityModeBackgroundController == null) {
            probabilityModeBackgroundController = new ProbabilityModeBackgroundController();
        }
        return probabilityModeBackgroundController;
    }

}
