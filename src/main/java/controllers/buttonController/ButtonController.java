package controllers.buttonController;

import controllers.backgroundController.BackgroundController;
import engine.display.DisplayBean;
import engine.display.Drawable;
import engine.display.DrawableFactory;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class ButtonController {

    private static ButtonController buttonController;
    private final CardsNumberChanger cardsNumberChanger;
    private final PlayerChanger playerChanger;
    private final GameRestarter gameRestarter;
    private final CardsOrderChanger cardsOrderChanger;
    private final GameEditSwitch gameEditSwitch;
    private final SolverStarter solverStarter;
    private final SolverSettingsSwitch solverSettingsSwitch;
    private final ShowStatisticsSwitch showStatisticsSwitch;

    private ButtonController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable gameButtonsSpace = getBackgroundController().getButtonsSpace().getButtonsSpace();

        cardsNumberChanger = new CardsNumberChanger(drawableFactory, gameButtonsSpace);
        playerChanger = new PlayerChanger(drawableFactory, gameButtonsSpace);
        gameRestarter = new GameRestarter(drawableFactory, gameButtonsSpace);
        cardsOrderChanger = new CardsOrderChanger(drawableFactory, gameButtonsSpace);
        gameEditSwitch = new GameEditSwitch(drawableFactory, gameButtonsSpace);
        solverStarter = new SolverStarter(drawableFactory, gameButtonsSpace);
        solverSettingsSwitch = new SolverSettingsSwitch(drawableFactory, gameButtonsSpace);
        showStatisticsSwitch = new ShowStatisticsSwitch(drawableFactory, gameButtonsSpace);
    }

    public static ButtonController getButtonController() {
        if (buttonController == null) {
            buttonController = new ButtonController();
        }
        return buttonController;
    }

}
