package controllers.gameControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class ProbabilityModeSwitch {

    private static class SwitchToProbabilityMode implements Command {

        @Override
        public void execute() {
            getScene().switchCollection("probabilityMode");
        }

    }

    private final SimpleButton probabilityModeSwitch;

    ProbabilityModeSwitch(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                520 + buttonsSpace.getY(),
                263,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Probability mode",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        probabilityModeSwitch = new SimpleButton(drawable, activationCombination, new SwitchToProbabilityMode());
        getScene().addObjectHigherThan(probabilityModeSwitch, buttonsSpace);
    }

}
