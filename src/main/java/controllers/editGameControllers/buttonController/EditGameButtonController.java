package controllers.editGameControllers.buttonController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static controllers.editGameControllers.backgroundController.EditGameBackgroundController.getEditGameBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class EditGameButtonController {

    private static EditGameButtonController editGameButtonController;
    private final StartingPlayerChanger startingPlayerChanger;
    private final AtuChanger atuChanger;
    private final CardsNumberChanger cardsNumberChanger;
    private final AcceptChanges acceptChanges;
    private final ClearChanges clearChanges;

    private EditGameButtonController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable background = getEditGameBackgroundController().getBackground().getBackground();

        startingPlayerChanger = new StartingPlayerChanger(drawableFactory, background);
        atuChanger = new AtuChanger(drawableFactory, background);
        cardsNumberChanger = new CardsNumberChanger(drawableFactory, background);
        acceptChanges = new AcceptChanges(drawableFactory, background);
        clearChanges = new ClearChanges(drawableFactory, background);
    }

    public static EditGameButtonController getEditGameButtonController() {
        if (editGameButtonController == null) {
            editGameButtonController = new EditGameButtonController();
        }
        return editGameButtonController;
    }

}
