package controllers.editGameControllers.buttonController;

import engine.display.DrawableFactory;

import static engine.display.DisplayBean.getDisplay;

public class EditGameButtonController {

    private static EditGameButtonController editGameButtonController;

    private EditGameButtonController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();

    }

    public static EditGameButtonController getEditGameButtonController() {
        if (editGameButtonController == null) {
            editGameButtonController = new EditGameButtonController();
        }
        return editGameButtonController;
    }
    
}
