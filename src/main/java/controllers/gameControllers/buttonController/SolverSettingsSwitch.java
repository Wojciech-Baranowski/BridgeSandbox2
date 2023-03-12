package controllers.gameControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class SolverSettingsSwitch {

    private static class SwitchToSolverSettingsCommand implements Command {

        @Override
        public void execute() {
            getScene().switchCollection("solverSettings");
        }

    }

    private final SimpleButton solverSettingsSwitch;

    SolverSettingsSwitch(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                470 + buttonsSpace.getY(),
                242,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Solver settings",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        solverSettingsSwitch = new SimpleButton(drawable, activationCombination, new SwitchToSolverSettingsCommand());
        getScene().addObjectHigherThan(solverSettingsSwitch, buttonsSpace);
    }

}
