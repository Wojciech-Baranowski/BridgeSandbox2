package controllers.editGameControllers.textController;

import controllers.editGameControllers.backgroundController.EditGameBackgroundController;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static controllers.editGameControllers.backgroundController.EditGameBackgroundController.getEditGameBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class EditGameTextController {

    private static EditGameTextController editGameTextController;
    private final PlayerSymbols playerSymbols;
    private final StartingPlayerChoose startingPlayerChoose;
    private final AtuChoose atuChoose;
    @Getter
    private final CardNumber cardNumber;
    @Getter
    private final InvalidGameData invalidGameData;

    private EditGameTextController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        EditGameBackgroundController backgroundController = getEditGameBackgroundController();
        Drawable background = backgroundController.getBackground().getBackground();
        Drawable[][] cells = backgroundController.getTable().getCells();

        playerSymbols = new PlayerSymbols(drawableFactory, cells);
        startingPlayerChoose = new StartingPlayerChoose(drawableFactory, background);
        atuChoose = new AtuChoose(drawableFactory, background);
        cardNumber = new CardNumber(drawableFactory, background);
        invalidGameData = new InvalidGameData(drawableFactory, background);
    }

    public static EditGameTextController getEditGameTextController() {
        if (editGameTextController == null) {
            editGameTextController = new EditGameTextController();
        }
        return editGameTextController;
    }

}
