package controllers.probabilityModeControllers.buttonController;

import controllers.probabilityModeControllers.backgroundController.ProbabilityModeBackgroundController;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static controllers.probabilityModeControllers.backgroundController.ProbabilityModeBackgroundController.getProbabilityModeBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class ProbabilityModeButtonController {

    private static ProbabilityModeButtonController probabilityModeButtonController;
    @Getter
    private final StartingPlayerChanger startingPlayerChanger;
    private final GameSwitch gameSwitch;
    private final ProbabilitySolverStarter probabilitySolverStarter;
    @Getter
    private PlayedCardsChanger playedCardsChanger;
    private ClearChanges clearChanges;

    private ProbabilityModeButtonController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable background = getProbabilityModeBackgroundController().getBackground().getBackground();
        ProbabilityModeBackgroundController probabilityModeBackgroundController =
                getProbabilityModeBackgroundController();
        startingPlayerChanger = new StartingPlayerChanger(drawableFactory, background);
        gameSwitch = new GameSwitch(drawableFactory, background);
        probabilitySolverStarter = new ProbabilitySolverStarter(drawableFactory, background);
        playedCardsChanger = new PlayedCardsChanger(drawableFactory,
                probabilityModeBackgroundController.getCardSlots().getCardSlots());
        clearChanges = new ClearChanges(drawableFactory, background);
    }

    public static ProbabilityModeButtonController getProbabilityModeButtonController() {
        if (probabilityModeButtonController == null) {
            probabilityModeButtonController = new ProbabilityModeButtonController();
        }
        return probabilityModeButtonController;
    }

}
