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
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static controllers.main.assets.NumberRounder.round;
import static java.lang.Math.sqrt;
import static java.util.Collections.sort;

@Getter
public class MultipleGamesSolver {

    private final static Random random = new Random();
    private final static int THREAD_NUMBER = 6;
    private final TimeStatistics timeStatistics;
    private final VisitedNodesStatistics visitedNodesStatistics;
    private final Algorithm algorithm;
    private final int gameNumber;
    private final int cardNumber;
    private final List<Long> numberOfVisitedNodesList;
    private final List<Double> computationTimeList;
    private int numberOfProcessedGames;


    public MultipleGamesSolver(Algorithm algorithm, int gameNumber, int cardNumber) {
        timeStatistics = new TimeStatistics();
        visitedNodesStatistics = new VisitedNodesStatistics();
        this.algorithm = algorithm;
        this.gameNumber = gameNumber;
        this.cardNumber = cardNumber;
        numberOfVisitedNodesList = new ArrayList<>();
        computationTimeList = new ArrayList<>();
        algorithm.preprocessing();
    }

    public void solveMultipleGames() {
        numberOfProcessedGames = 0;
        ForkJoinPool threadPool = new ForkJoinPool(THREAD_NUMBER);
        for (int i = 0; i < gameNumber; i++) {
            threadPool.execute(this::solveGame);
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        updateTimeStatistics(computationTimeList);
        updateVisitedNodesStatistics(numberOfVisitedNodesList);
        moveStatisticsToClipboard();
    }

    private void solveGame() {
        Game game = Game.getGameMultipliedInstance();
        int randomNumber = random.nextInt();
        if (randomNumber < 0) {
            randomNumber *= -1;
        }
        game.initializeGame(Color.values()[randomNumber % 5], cardNumber);
        long time = System.currentTimeMillis();
        algorithm.solve(game);
        time = System.currentTimeMillis() - time;
        long numberOfVisitedNodes = algorithm.getNumberOfVisitedNodes();
        double computationTime = round(time / 1000.0, 4);
        synchronized (this) {
            numberOfVisitedNodesList.add(numberOfVisitedNodes);
            computationTimeList.add(computationTime);
            numberOfProcessedGames++;
            Logger.log(numberOfProcessedGames + " / " + gameNumber);
        }
    }


    private void updateTimeStatistics(List<Double> times) {
        sort(times);
        int size = times.size();
        double tot = times.stream().reduce(0.0, Double::sum);
        double max = times.get(size - 1);
        double avg = tot / size;
        double med = times.get(size / 2);
        double dev = sqrt(times.stream().map(t -> t * t).reduce(0.0, Double::sum) / size - avg * avg);
        double p90 = times.get(size * 90 / 100);
        double p95 = times.get(size * 95 / 100);
        double p99 = times.get(size * 99 / 100);
        tot = round(tot, 4);
        max = round(max, 4);
        avg = round(avg, 4);
        med = round(med, 4);
        dev = round(dev, 4);
        p90 = round(p90, 4);
        p95 = round(p95, 4);
        p99 = round(p99, 4);
        timeStatistics.update(tot, max, avg, med, dev, p90, p95, p99);
    }

    private void updateVisitedNodesStatistics(List<Long> modes) {
        sort(modes);
        int size = modes.size();
        long tot = modes.stream().reduce(0L, Long::sum);
        long max = modes.get(size - 1);
        long avg = tot / size;
        long med = modes.get(size / 2);
        long dev = (long) sqrt(modes.stream().map(t -> t * t).reduce(0L, Long::sum) / (double) size - avg * avg);
        long p90 = modes.get(size * 90 / 100);
        long p95 = modes.get(size * 95 / 100);
        long p99 = modes.get(size * 99 / 100);
        visitedNodesStatistics.update(tot, max, avg, med, dev, p90, p95, p99);
    }

    private void moveStatisticsToClipboard() {
        String timeStatisticsCSV = timeStatistics.getCSVData();
        String visitedNodesStatisticsCSV = visitedNodesStatistics.getCSVData();
        StringSelection clipboardText = new StringSelection(timeStatisticsCSV + ";" + visitedNodesStatisticsCSV);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipboardText, null);
    }

}
