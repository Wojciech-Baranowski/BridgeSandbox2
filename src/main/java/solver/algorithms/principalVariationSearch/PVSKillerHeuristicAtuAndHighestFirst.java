package solver.algorithms.principalVariationSearch;

import gameLogic.game.Game;
import solver.result.Result;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PVSKillerHeuristicAtuAndHighestFirst extends PrincipalVariationSearch {

    public byte atu;

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        PVSNode PVSNode = new PVSNode(game);
        atu = PVSNode.atu;
        moveHighestAndAtuToFirstPosition(PVSNode);
        byte bestOutcome = principalVariationSearch(PVSNode);
        return Result.mapResponseToResult(game, PVSNode.allOutcomeCards, bestOutcome);
    }

    protected void moveHighestAndAtuToFirstPosition(PVSNode PVSNode) {
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            quickSort(PVSNode.cards[i], (byte) 0, (byte) (PVSNode.cardsSize[i] - 1));
        }
    }

    protected void quickSort(byte[] cards, byte beg, byte end) {
        if (beg < end) {
            byte pivot = cards[end];
            byte i = (byte) (beg - 1);
            for (byte j = beg; j < end; j++) {
                if ((cards[j] % FIGURE_NUMBER + ((cards[j] / FIGURE_NUMBER == atu) ? FIGURE_NUMBER : 0)
                        >= pivot % FIGURE_NUMBER + ((pivot / FIGURE_NUMBER == atu) ? FIGURE_NUMBER : 0))) {
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
