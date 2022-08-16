package controllers.solverSettingsControllers.buttonController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static controllers.gameControllers.backgroundController.GameBackgroundController.getGameBackgroundController;
import static controllers.solverSettingsControllers.backgroundController.SolverSettingsBackgroundController.getSolverSettingsBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class SolverSettingsButtonController {

    private static SolverSettingsButtonController solverSettingsButtonController;
    private final CardsNumberChanger cardsNumberChanger;
    private final GamesNumberChanger gamesNumberChanger;
    private final MultipleGamesSolverStarter multipleGamesSolverStarter;
    private final GameSwitch gameSwitch;
    private final StatisticsSwitch statisticsSwitch;

    private SolverSettingsButtonController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable background = getSolverSettingsBackgroundController().getBackground().getBackground();
        Drawable buttonsSpace = getSolverSettingsBackgroundController().getButtonsSpace().getButtonsSpace();

        cardsNumberChanger = new CardsNumberChanger(drawableFactory, buttonsSpace);
        gamesNumberChanger = new GamesNumberChanger(drawableFactory, buttonsSpace);
        multipleGamesSolverStarter = new MultipleGamesSolverStarter(drawableFactory, buttonsSpace);
        gameSwitch = new GameSwitch(drawableFactory, background);
        statisticsSwitch = new StatisticsSwitch(drawableFactory, background);
    }

    public static SolverSettingsButtonController getSolverSettingsButtonController() {
        if(solverSettingsButtonController == null) {
            solverSettingsButtonController = new SolverSettingsButtonController();
        }
        return solverSettingsButtonController;
    }

}
