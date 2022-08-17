package controllers.gameControllers.buttonController;

import controllers.solverSettingsControllers.algorithmsController.AlgorithmsChanger;
import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import solver.Result;
import solver.ResultRound;

import static controllers.gameControllers.cardController.GameCardController.getGameCardController;
import static controllers.gameControllers.historyController.GameHistoryController.getGameHistoryController;
import static controllers.gameControllers.textController.GameTextController.getGameTextController;
import static controllers.solverSettingsControllers.algorithmsController.SolverSettingsAlgorithmsController.getSolverSettingsAlgorithmsController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;

public class SolverStarter {

    private static class StartSolverCommand implements Command {

        @Override
        public void execute() {
            AlgorithmsChanger algorithmsChanger = getSolverSettingsAlgorithmsController().getAlgorithmsChanger();
            int chosenSolverIndex = algorithmsChanger.getAlgorithmsBundle().getSelectedRadioButtonIndex();
            Result result = algorithmsChanger.getSolvers().get(chosenSolverIndex).getAlgorithm().solve(getGame());
            for (ResultRound round : result.getResultRounds()) {
                getGameHistoryController().addHistoryEntry(round);
            }
            getGameCardController().removeAllCards();
            getGameTextController().updatePoints(result.getPoints());
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
