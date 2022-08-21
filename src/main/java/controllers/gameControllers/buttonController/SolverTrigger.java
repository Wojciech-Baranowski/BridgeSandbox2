package controllers.gameControllers.buttonController;

import controllers.solverSettingsControllers.algorithmsController.AlgorithmsChanger;
import engine.button.SimpleButton;
import engine.button.checkbox.CommandCheckbox;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.game.Game;
import lombok.Getter;
import solver.result.Result;
import solver.result.ResultRound;

import static controllers.gameControllers.historyController.GameHistoryController.getGameHistoryController;
import static controllers.gameControllers.textController.GameTextController.getGameTextController;
import static controllers.solverSettingsControllers.algorithmsController.SolverSettingsAlgorithmsController.getSolverSettingsAlgorithmsController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;

public class SolverTrigger {

    private static class StartSolverCommand implements Command {

        @Override
        public void execute() {
            Game game = getGame();
            AlgorithmsChanger algorithmsChanger = getSolverSettingsAlgorithmsController().getAlgorithmsChanger();
            int chosenAlgorithmIndex = algorithmsChanger.getAlgorithmsBundle().getSelectedRadioButtonIndex();
            Result result = algorithmsChanger.getAlgorithms().get(chosenAlgorithmIndex).solve(game);
            getGameHistoryController().removeAllPredictedHistoryEntries();
            for (ResultRound round : result.getResultRounds()) {
                getGameHistoryController().addHistoryEntry(round);
            }
            getGameTextController().updatePredictedPoints(result.getPoints());
        }

    }

    private static class StopSolverCommand implements Command {

        @Override
        public void execute() {
            getGameHistoryController().removeAllPredictedHistoryEntries();
            getGameTextController().updatePredictedPoints(null);
        }

    }

    @Getter
    private final CommandCheckbox solverTrigger;

    SolverTrigger(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable inactiveBackground = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                370 + buttonsSpace.getY(),
                224,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable activeBackground = drawableFactory.makeFramedRectangle(
                inactiveBackground.getX(),
                inactiveBackground.getY(),
                inactiveBackground.getW(),
                inactiveBackground.getH(),
                2,
                "lightBlue",
                "yellow");

        Drawable text = drawableFactory.makeText(
                "Toggle solver",
                8 + inactiveBackground.getX(),
                5 + inactiveBackground.getY(),
                "HBE32",
                "black");

        DrawableComposition inactiveDrawable = new DrawableComposition(inactiveBackground, text);
        DrawableComposition activeDrawable = new DrawableComposition(activeBackground, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        solverTrigger = new CommandCheckbox(
                inactiveDrawable,
                activeDrawable,
                activationCombination,
                new StartSolverCommand(),
                new StopSolverCommand());
        getScene().addObjectHigherThan(solverTrigger, buttonsSpace);
    }

}
