package controllers.solverSettingsControllers.buttonController;

import controllers.solverSettingsControllers.algorithmsController.AlgorithmsChanger;
import controllers.solverSettingsControllers.textController.SolverSettingsTextController;
import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import engine.main.Logger;
import gameLogic.card.Color;
import gameLogic.game.Game;
import solver.Algorithm;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

import static controllers.main.assets.NumberRounder.round;
import static controllers.solverSettingsControllers.algorithmsController.SolverSettingsAlgorithmsController.getSolverSettingsAlgorithmsController;
import static controllers.solverSettingsControllers.buttonController.SolverSettingsButtonController.getSolverSettingsButtonController;
import static controllers.solverSettingsControllers.textController.SolverSettingsTextController.getSolverSettingsTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;
import static java.lang.Math.sqrt;
import static java.util.Collections.sort;

public class MultipleGamesSolverStarter {

    private static class StartMultipleGamesSolverCommand implements Command {

        @Override
        public void execute() {
            Game game = getGame();
            SolverSettingsButtonController buttonController = getSolverSettingsButtonController();
            SolverSettingsTextController textController = getSolverSettingsTextController();
            AlgorithmsChanger algorithmsChanger = getSolverSettingsAlgorithmsController().getAlgorithmsChanger();

            int algorithmIndex = algorithmsChanger.getAlgorithmsBundle().getSelectedRadioButtonIndex();
            Algorithm algorithm = algorithmsChanger.getAlgorithms().get(algorithmIndex);
            int cardNumber = buttonController.getCardsNumberChanger().getCardNumber();
            int gameNumber = buttonController.getGamesNumberChanger().getGamesNumber();
            List<Long> numberOfVisitedNodesList = new ArrayList<>();
            List<Double> computationTimeList = new ArrayList<>();

            for (int i = 0; i < gameNumber; i++) {
                game.initializeGame(Color.CLUB, cardNumber);
                long time = System.currentTimeMillis();
                algorithm.solve(game);
                time = System.currentTimeMillis() - time;
                numberOfVisitedNodesList.add(algorithm.getNumberOfVisitedNodes());
                computationTimeList.add(round(time / 1000.0, 4));
                Logger.log((i + 1) + " / " + gameNumber);
            }
            updateTimeStatistics(computationTimeList);
            updateVisitedNodesStatistics(numberOfVisitedNodesList);
            moveStatisticsToClipboard();
        }

        private void updateTimeStatistics(List<Double> timeList) {
            SolverSettingsTextController textController = getSolverSettingsTextController();
            sort(timeList);
            int size = timeList.size();
            double tot = timeList.stream().reduce(0.0, Double::sum);
            double max = timeList.get(size - 1);
            double avg = tot / size;
            double med = timeList.get(size / 2);
            double dev = sqrt(timeList.stream().map(t -> t * t).reduce(0.0, Double::sum) / size - avg * avg);
            double p90 = timeList.get(size * 90 / 100);
            double p95 = timeList.get(size * 95 / 100);
            double p99 = timeList.get(size * 99 / 100);
            tot = round(tot, 3);
            max = round(max, 3);
            avg = round(avg, 3);
            med = round(med, 3);
            dev = round(dev, 3);
            p90 = round(p90, 3);
            p95 = round(p95, 3);
            p99 = round(p99, 3);
            textController.getTimeStatistics().update(tot, max, avg, med, dev, p90, p95, p99);
        }

        private void updateVisitedNodesStatistics(List<Long> nodesList) {
            SolverSettingsTextController textController = getSolverSettingsTextController();
            sort(nodesList);
            int size = nodesList.size();
            long tot = nodesList.stream().reduce(0L, Long::sum);
            long max = nodesList.get(size - 1);
            long avg = tot / size;
            long med = nodesList.get(size / 2);
            long dev = (long) sqrt(nodesList.stream().map(t -> t * t).reduce(0L, Long::sum) - avg * avg);
            long p90 = nodesList.get(size * 90 / 100);
            long p95 = nodesList.get(size * 95 / 100);
            long p99 = nodesList.get(size * 99 / 100);
            textController.getVisitedNodesStatistics().update(tot, max, avg, med, dev, p90, p95, p99);
        }

        private void moveStatisticsToClipboard() {
            SolverSettingsTextController textController = getSolverSettingsTextController();
            String timeStatistics = textController.getTimeStatistics().getCSVData();
            String visitedNodesStatistics = textController.getVisitedNodesStatistics().getCSVData();
            StringSelection clipboardContent = new StringSelection(timeStatistics + ";" + visitedNodesStatistics);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipboardContent, null);
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
