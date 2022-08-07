package controllers.buttonController;

import controllers.backgroundController.BackgroundController;
import engine.display.DisplayBean;
import engine.display.DrawableFactory;

import static controllers.backgroundController.BackgroundController.getBackgroundController;

public class ButtonController {

    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
    private static final BackgroundController backgroundController = getBackgroundController();
    private static ButtonController buttonController;
    private final CardsNumberChanger cardsNumberChanger;
    private final PlayerChanger playerChanger;
    private final GameRestarter gameRestarter;
    private final CardsOrderChanger cardsOrderChanger;

    private ButtonController() {
        cardsNumberChanger =
                new CardsNumberChanger(drawableFactory, backgroundController.getButtonsSpace().getButtonsSpace());
        playerChanger = new PlayerChanger(drawableFactory, backgroundController.getButtonsSpace().getButtonsSpace());
        gameRestarter = new GameRestarter(drawableFactory, backgroundController.getButtonsSpace().getButtonsSpace());
        cardsOrderChanger =
                new CardsOrderChanger(drawableFactory, backgroundController.getButtonsSpace().getButtonsSpace());
    }

    public static ButtonController getButtonController() {
        if (buttonController == null) {
            buttonController = new ButtonController();
        }
        return buttonController;
    }

}
