package solver.multipleGamesSolver;

import engine.main.Logger;
import gameLogic.card.Color;
import gameLogic.game.Game;
import lombok.Getter;
import solver.Algorithm;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

import static controllers.main.assets.NumberRounder.round;
import static gameLogic.game.Game.getGame;
import static java.lang.Math.sqrt;
import static java.util.Collections.sort;

@Getter
public class MultipleGamesSolver {

    private final TimeStatistics timeStatistics;
    private final VisitedNodesStatistics visitedNodesStatistics;
    private final Algorithm algorithm;
    private final int gameNumber;
    private final int cardNumber;


    public MultipleGamesSolver(Algorithm algorithm, int gameNumber, int cardNumber) {
        timeStatistics = new TimeStatistics();
        visitedNodesStatistics = new VisitedNodesStatistics();
        this.algorithm = algorithm;
        this.gameNumber = gameNumber;
        this.cardNumber = cardNumber;
    }

    public void solve() {
        List<Long> numberOfVisitedNodesList = new ArrayList<>();
        List<Double> computationTimeList = new ArrayList<>();
        Game game = getGame();

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
        timeStatistics.update(tot, max, avg, med, dev, p90, p95, p99);
    }

    private void updateVisitedNodesStatistics(List<Long> nodesList) {
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
        visitedNodesStatistics.update(tot, max, avg, med, dev, p90, p95, p99);
    }

    private void moveStatisticsToClipboard() {
        String timeStatisticsCSV = timeStatistics.getCSVData();
        String visitedNodesStatisticsCSV = visitedNodesStatistics.getCSVData();
        StringSelection clipboardText = new StringSelection(timeStatisticsCSV + ";" + visitedNodesStatisticsCSV);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipboardText, null);
    }

}
