package solver.algorithms.killerHeuristic;

import gameLogic.game.Game;
import solver.result.Result;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class KillerHeuristicHighestFirst extends KillerHeuristic {

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        Node node = new Node(game);
        orderByFiguresDescending(node);
        byte bestOutcome = alphaBeta(node);
        return Result.mapResponseToResult(game, node.allOutcomeCards, bestOutcome);
    }

    private void orderByFiguresDescending(Node node) {
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            quickSort(node.cards[i], (byte) 0, (byte) (node.cardsSize[i] - 1));
        }
    }

    private void quickSort(byte[] cards, byte beg, byte end) {
        if (beg < end) {
            byte pivot = cards[end];
            byte i = (byte) (beg - 1);
            for (byte j = beg; j < end; j++) {
                if (cards[j] % FIGURE_NUMBER >= pivot % FIGURE_NUMBER) {
                    i++;
                    byte swap = cards[i];
                    cards[i] = cards[j];
                    cards[j] = swap;
                }
            }
            byte swap = cards[i + 1];
            cards[i + 1] = cards[end];
            cards[end] = swap;
            quickSort(cards, beg, (i));
            quickSort(cards, (byte) (i + 2), end);
        }
    }

}
