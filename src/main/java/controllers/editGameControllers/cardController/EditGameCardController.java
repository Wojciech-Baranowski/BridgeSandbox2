package controllers.editGameControllers.cardController;

import engine.display.DrawableFactory;

import static engine.display.DisplayBean.getDisplay;

public class EditGameCardController {

    private static EditGameCardController editGameCardController;

    private EditGameCardController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();

    }

    public static EditGameCardController getEditGameCardController() {
        if (editGameCardController == null) {
            editGameCardController = new EditGameCardController();
        }
        return editGameCardController;
    }

}
