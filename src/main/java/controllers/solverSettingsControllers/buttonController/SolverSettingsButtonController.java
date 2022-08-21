package controllers.solverSettingsControllers.buttonController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static controllers.solverSettingsControllers.backgroundController.SolverSettingsBackgroundController.getSolverSettingsBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class SolverSettingsButtonController {

    private static SolverSettingsButtonController solverSettingsButtonController;
    @Getter
    private final CardsNumberChanger cardsNumberChanger;
    @Getter
    private final GamesNumberChanger gamesNumberChanger;
    private final MultipleGamesSolverStarter multipleGamesSolverStarter;
    private final GameSwitch gameSwitch;

    private SolverSettingsButtonController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable background = getSolverSettingsBackgroundController().getBackground().getBackground();
        Drawable buttonsSpace = getSolverSettingsBackgroundController().getButtonsSpace().getButtonsSpace();

        cardsNumberChanger = new CardsNumberChanger(drawableFactory, buttonsSpace);
        gamesNumberChanger = new GamesNumberChanger(drawableFactory, buttonsSpace);
        multipleGamesSolverStarter = new MultipleGamesSolverStarter(drawableFactory, buttonsSpace);
        gameSwitch = new GameSwitch(drawableFactory, background);
    }

    public static SolverSettingsButtonController getSolverSettingsButtonController() {
        if(solverSettingsButtonController == null) {
            solverSettingsButtonController = new SolverSettingsButtonController();
        }
        return solverSettingsButtonController;
    }

}
