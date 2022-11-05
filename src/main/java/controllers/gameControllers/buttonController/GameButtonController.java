package controllers.gameControllers.buttonController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static controllers.gameControllers.backgroundController.GameBackgroundController.getGameBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class GameButtonController {

    private static GameButtonController gameButtonController;
    private final CardsNumberChanger cardsNumberChanger;
    private final PlayerChanger playerChanger;
    private final GameRestarter gameRestarter;
    private final CardsOrderChanger cardsOrderChanger;
    private final GameEditSwitch gameEditSwitch;
    @Getter
    private final SolverTrigger solverTrigger;
    private final SolverSettingsSwitch solverSettingsSwitch;
    private final ProbabilityModeSwitch probabilityModeSwitch;

    private GameButtonController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable gameButtonsSpace = getGameBackgroundController().getButtonsSpace().getButtonsSpace();

        cardsNumberChanger = new CardsNumberChanger(drawableFactory, gameButtonsSpace);
        playerChanger = new PlayerChanger(drawableFactory, gameButtonsSpace);
        gameRestarter = new GameRestarter(drawableFactory, gameButtonsSpace);
        cardsOrderChanger = new CardsOrderChanger(drawableFactory, gameButtonsSpace);
        gameEditSwitch = new GameEditSwitch(drawableFactory, gameButtonsSpace);
        solverTrigger = new SolverTrigger(drawableFactory, gameButtonsSpace);
        solverSettingsSwitch = new SolverSettingsSwitch(drawableFactory, gameButtonsSpace);
        probabilityModeSwitch = new ProbabilityModeSwitch(drawableFactory, gameButtonsSpace);
    }

    public static GameButtonController getGameButtonController() {
        if (gameButtonController == null) {
            gameButtonController = new GameButtonController();
        }
        return gameButtonController;
    }

}
