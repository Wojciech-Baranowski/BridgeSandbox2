package controllers.editGameControllers.backgroundController;

import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.display.DisplayBean.getDisplay;

public class EditGameBackgroundController {

    private static EditGameBackgroundController editGameBackgroundController;
    @Getter
    private final Background background;
    @Getter
    private final Table table;

    private EditGameBackgroundController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        background = new Background(drawableFactory);
        table = new Table(drawableFactory, background.getBackground());
    }

    public static EditGameBackgroundController getEditGameBackgroundController() {
        if (editGameBackgroundController == null) {
            editGameBackgroundController = new EditGameBackgroundController();
        }
        return editGameBackgroundController;
    }

}
