package controllers.editGameControllers.cardController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static controllers.editGameControllers.backgroundController.EditGameBackgroundController.getEditGameBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class EditGameCardController {

    private static EditGameCardController editGameCardController;
    private final ChooseCards chooseCards;

    private EditGameCardController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable[][] cells = getEditGameBackgroundController().getTable().getCells();
        chooseCards = new ChooseCards(drawableFactory, cells);
    }

    public static EditGameCardController getEditGameCardController() {
        if (editGameCardController == null) {
            editGameCardController = new EditGameCardController();
        }
        return editGameCardController;
    }

}
