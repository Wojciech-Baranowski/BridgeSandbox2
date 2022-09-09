package solver.algorithms.principalVariationSearch;

import gameLogic.game.Game;
import solver.result.Result;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PVSKillerHeuristicAtuFirst extends PrincipalVariationSearch {

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        PVSNode PVSNode = new PVSNode(game);
        moveAtuToFirstPosition(PVSNode);
        byte bestOutcome = principalVariationSearch(PVSNode);
        return Result.mapResponseToResult(game, PVSNode.allOutcomeCards, bestOutcome);
    }

    protected void moveAtuToFirstPosition(PVSNode PVSNode) {
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            byte[] temporaryCards = new byte[PVSNode.cardsSize[i]];
            byte temporaryCardsSize = 0;
            for (int j = 0; j < PVSNode.cardsSize[i]; j++) {
                if (PVSNode.cards[i][j] / FIGURE_NUMBER == PVSNode.atu) {
                    temporaryCards[temporaryCardsSize] = PVSNode.cards[i][j];
                    temporaryCardsSize++;
                }
            }
            for (int j = 0; j < PVSNode.cardsSize[i]; j++) {
                if (PVSNode.cards[i][j] / FIGURE_NUMBER != PVSNode.atu) {
                    temporaryCards[temporaryCardsSize] = PVSNode.cards[i][j];
                    temporaryCardsSize++;
                }
            }
            for (int j = 0; j < PVSNode.cardsSize[i]; j++) {
                PVSNode.cards[i][j] = temporaryCards[j];
            }
        }
    }

}
