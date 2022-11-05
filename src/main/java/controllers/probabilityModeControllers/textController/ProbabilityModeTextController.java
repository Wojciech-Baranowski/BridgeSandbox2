package controllers.probabilityModeControllers.textController;

import controllers.probabilityModeControllers.backgroundController.ProbabilityModeBackgroundController;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static controllers.probabilityModeControllers.backgroundController.ProbabilityModeBackgroundController.getProbabilityModeBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class ProbabilityModeTextController {

    private static ProbabilityModeTextController probabilityModeTextController;
    private final PlayerSymbols playerSymbols;
    private final PlayedCards playedCards;
    private final StartingPlayer startingPlayer;
    @Getter
    private final InvalidGameData invalidGameData;
    @Getter
    private final Probabilities probabilities;

    private ProbabilityModeTextController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable background = getProbabilityModeBackgroundController().getBackground().getBackground();
        ProbabilityModeBackgroundController probabilityModeBackgroundController =
                getProbabilityModeBackgroundController();
        playerSymbols = new PlayerSymbols(drawableFactory,
                probabilityModeBackgroundController.getPlayerSymbolSpace().getPlayerSymbolSlots());
        playedCards = new PlayedCards(drawableFactory, background);
        startingPlayer = new StartingPlayer(drawableFactory, background);
        invalidGameData = new InvalidGameData(drawableFactory, background);
        probabilities = new Probabilities(drawableFactory, background);
    }

    public static ProbabilityModeTextController getProbabilityModeTextController() {
        if (probabilityModeTextController == null) {
            probabilityModeTextController = new ProbabilityModeTextController();
        }
        return probabilityModeTextController;
    }

}
