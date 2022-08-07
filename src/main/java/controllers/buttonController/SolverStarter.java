package controllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class SolverStarter {

    private static class StartSolverCommand implements Command {

        @Override
        public void execute() {

        }

    }

    private final SimpleButton solverStarter;

    SolverStarter(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                370 + buttonsSpace.getY(),
                100,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Solve",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        solverStarter = new SimpleButton(drawable, activationCombination, new StartSolverCommand());
        getScene().addObjectHigherThan(solverStarter, buttonsSpace);
    }

}
