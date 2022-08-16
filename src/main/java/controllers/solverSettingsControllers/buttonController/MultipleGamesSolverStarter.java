package controllers.solverSettingsControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class MultipleGamesSolverStarter {

    private static class StartMultipleGamesSolverCommand implements Command {

        @Override
        public void execute() {

        }

    }

    private final SimpleButton multipleGamesSolverStarter;

    MultipleGamesSolverStarter(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                20 + buttonsSpace.getX(),
                140 + buttonsSpace.getY(),
                560,
                80,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Solve multiple games",
                40 + background.getX(),
                16 + background.getY(),
                "HBE48",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        multipleGamesSolverStarter =
                new SimpleButton(drawable, activationCombination, new StartMultipleGamesSolverCommand());
        getScene().addObjectHigherThan(multipleGamesSolverStarter, buttonsSpace);
    }

}
