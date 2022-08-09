package controllers.editGameControllers.textController;

import engine.display.DrawableFactory;

import static engine.display.DisplayBean.getDisplay;

public class EditGameTextController {

    private static EditGameTextController editGameTextController;

    private EditGameTextController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();

    }

    public static EditGameTextController getEditGameTextController() {
        if (editGameTextController == null) {
            editGameTextController = new EditGameTextController();
        }
        return editGameTextController;
    }

}
