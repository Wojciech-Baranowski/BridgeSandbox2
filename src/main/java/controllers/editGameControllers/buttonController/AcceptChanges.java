package controllers.editGameControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static controllers.editGameControllers.textController.EditGameTextController.getEditGameTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class AcceptChanges {

    private class AcceptCommand implements Command {

        @Override
        public void execute() {
            if(isGameValid()) {
                getScene().switchCollection("game");
            } else {
                getEditGameTextController().getInvalidGameData().showText();
            }
        }

    }

    private final SimpleButton acceptChanges;

    AcceptChanges(DrawableFactory drawableFactory, Drawable background) {
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                920 + background.getX(),
                546 + background.getY(),
                248,
                100,
                2,
                "gray",
                "lightBlue");

        Drawable buttonText = drawableFactory.makeText(
                "ACCEPT",
                28 + buttonBackground.getX(),
                28 + buttonBackground.getY(),
                "HBE48",
                "black");

        Drawable buttonDrawable = new DrawableComposition(buttonBackground, buttonText);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        acceptChanges = new SimpleButton(buttonDrawable, activationCombination, new AcceptCommand());
        getScene().addObjectHigherThan(acceptChanges, background);
    }

    private boolean isGameValid() {
        return false;
    }

}
