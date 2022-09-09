package solver.tester;

import gameLogic.card.Color;
import gameLogic.game.Game;
import solver.Algorithm;
import solver.algorithms.alphaBeta.AlphaBeta;
import solver.algorithms.principalVariationSearch.PVSWithCutoff;
import solver.algorithms.principalVariationSearch.PVSWithLookup;
import solver.result.Result;

public class Tester {

    private final int numberOfCards;
    private final int numberOfTests;
    private final Algorithm tested;
    private final Algorithm correct;

    public Tester() {
        numberOfCards = 4;
        numberOfTests = 10000;
        tested = new PVSWithLookup();
        correct = new AlphaBeta();
        test();
    }

    private void test() {
        tested.preprocessing();
        correct.preprocessing();
        Game game = Game.getGameMultipliedInstance();
        for (int i = 0; i < numberOfTests; i++) {
            game.initializeGame(Color.CLUB, numberOfCards);
            Result correctResult = correct.solve(game);
            Result testedResult = tested.solve(game);
            if (correctResult.getPoints()[0] + 100 * correctResult.getPoints()[1] !=
                    testedResult.getPoints()[0] + 100 * testedResult.getPoints()[1]) {
                System.out.println("ALGORITHM ISN'T CORRECT!");
                return;
            }
            System.out.println((i + 1) + " / " + numberOfTests);
        }
    }

    public static void main(String[] args) {
        new Tester();
        System.out.println("DONE!");
    }

}
