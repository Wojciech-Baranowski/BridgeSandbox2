package controllers.editGameControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class ClearChanges {

    private class ClearCommand implements Command {

        @Override
        public void execute() {

        }

    }

    private final SimpleButton clearChanges;

    ClearChanges(DrawableFactory drawableFactory, Drawable background) {
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                640 + background.getX(),
                546 + background.getY(),
                248,
                100,
                2,
                "gray",
                "lightBlue");

        Drawable buttonText = drawableFactory.makeText(
                "CLEAR",
                46 + buttonBackground.getX(),
                28 + buttonBackground.getY(),
                "HBE48",
                "black");

        Drawable buttonDrawable = new DrawableComposition(buttonBackground, buttonText);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        clearChanges = new SimpleButton(buttonDrawable, activationCombination, new ClearCommand());
        getScene().addObjectHigherThan(clearChanges, background);
    }

}
