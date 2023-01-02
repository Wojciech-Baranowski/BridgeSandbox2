package controllers.probabilityModeControllers.buttonController;

import controllers.probabilityModeControllers.textController.ProbabilityModeTextController;
import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import solver.probabilitySolver.ProbabilitySolver;

import static controllers.probabilityModeControllers.buttonController.ProbabilityModeButtonController.getProbabilityModeButtonController;
import static controllers.probabilityModeControllers.textController.ProbabilityModeTextController.getProbabilityModeTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.MIN_CARDS_PER_PLAYER;

public class LostTricksChanger {

    private static class DecrementLostTricksCommand implements Command {

        @Override
        public void execute() {
            ProbabilitySolver solver =
                    getProbabilityModeButtonController().getProbabilitySolverStarter().getProbabilitySolver();
            ProbabilityModeTextController textController = getProbabilityModeTextController();
            int currentLostTricksNumber = solver.getLostTricksNumber();
            if (currentLostTricksNumber > 0) {
                solver.setLostTricksNumber(currentLostTricksNumber - 1);
                textController.getLostTricks().updateLostTricks(currentLostTricksNumber - 1);
                if (solver.getLastProbabilities() != null) {
                    textController.getProbabilities().updateProbabilities(
                            solver.getLastProbabilities()[solver.getLostTricksNumber()],
                            solver.getLastProbabilitiesSums()[solver.getLostTricksNumber()]);
                }
            }
        }
    }

    private static class IncrementLostTricksCommand implements Command {

        @Override
        public void execute() {
            ProbabilitySolver solver =
                    getProbabilityModeButtonController().getProbabilitySolverStarter().getProbabilitySolver();
            ProbabilityModeTextController textController = getProbabilityModeTextController();
            int currentLostTricksNumber = solver.getLostTricksNumber();
            if (currentLostTricksNumber < MIN_CARDS_PER_PLAYER) {
                solver.setLostTricksNumber(currentLostTricksNumber + 1);
                textController.getLostTricks().updateLostTricks(currentLostTricksNumber + 1);
                if (solver.getLastProbabilities() != null) {
                    textController.getProbabilities().updateProbabilities(
                            solver.getLastProbabilities()[solver.getLostTricksNumber()],
                            solver.getLastProbabilitiesSums()[solver.getLostTricksNumber()]);
                }
            }
        }

    }

    private final SimpleButton incLostTricksButton;
    private final SimpleButton decLostTricksButton;

    LostTricksChanger(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                920 + buttonsSpace.getX(),
                20 + buttonsSpace.getY(),
                48,
                48,
                2,
                "gray",
                "lightBlue");

        Drawable symbol = drawableFactory.makeText(
                "-",
                16 + background.getX(),
                -1 + background.getY(),
                "HBE48",
                "black");

        Drawable decDrawable = new DrawableComposition(background, symbol);

        background = drawableFactory.makeFramedRectangle(
                136 + decDrawable.getX(),
                decDrawable.getY(),
                48,
                48,
                2,
                "gray",
                "lightBlue");

        symbol = drawableFactory.makeText(
                "+",
                10 + background.getX(),
                -1 + background.getY(),
                "HBE48",
                "black");

        Drawable incDrawable = new DrawableComposition(background, symbol);

        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        incLostTricksButton = new SimpleButton(incDrawable, activationCombination, new IncrementLostTricksCommand());
        decLostTricksButton = new SimpleButton(decDrawable, activationCombination, new DecrementLostTricksCommand());
        getScene().addObjectHigherThan(incLostTricksButton, buttonsSpace);
        getScene().addObjectHigherThan(decLostTricksButton, buttonsSpace);
    }

}
