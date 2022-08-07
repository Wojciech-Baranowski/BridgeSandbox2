package controllers.gameControllers.buttonController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static controllers.gameControllers.backgroundController.GameBackgroundController.getGameBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class GameButtonController {

    private static GameButtonController gameButtonController;
    private final CardsNumberChanger cardsNumberChanger;
    private final PlayerChanger playerChanger;
    private final GameRestarter gameRestarter;
    private final CardsOrderChanger cardsOrderChanger;
    private final GameEditSwitch gameEditSwitch;
    private final SolverStarter solverStarter;
    private final SolverSettingsSwitch solverSettingsSwitch;
    private final ShowStatisticsSwitch showStatisticsSwitch;

    private GameButtonController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable gameButtonsSpace = getGameBackgroundController().getButtonsSpace().getButtonsSpace();

        cardsNumberChanger = new CardsNumberChanger(drawableFactory, gameButtonsSpace);
        playerChanger = new PlayerChanger(drawableFactory, gameButtonsSpace);
        gameRestarter = new GameRestarter(drawableFactory, gameButtonsSpace);
        cardsOrderChanger = new CardsOrderChanger(drawableFactory, gameButtonsSpace);
        gameEditSwitch = new GameEditSwitch(drawableFactory, gameButtonsSpace);
        solverStarter = new SolverStarter(drawableFactory, gameButtonsSpace);
        solverSettingsSwitch = new SolverSettingsSwitch(drawableFactory, gameButtonsSpace);
        showStatisticsSwitch = new ShowStatisticsSwitch(drawableFactory, gameButtonsSpace);
    }

    public static GameButtonController getGameButtonController() {
        if (gameButtonController == null) {
            gameButtonController = new GameButtonController();
        }
        return gameButtonController;
    }

}
