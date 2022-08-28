package solver.algorithms.killerHeuristic;

import gameLogic.game.Game;
import solver.result.Result;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class KillerHeuristicAtuFirst extends KillerHeuristic {

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        Node node = new Node(game);
        moveAtuToFirstPosition(node);
        Response bestOutcome = alphaBeta(node);
        return mapResponseToResult(game, bestOutcome);
    }

    private void moveAtuToFirstPosition(Node node) {
        for(int i = 0; i < PLAYER_NUMBER; i++) {
            byte[] temporaryCards = new byte[node.cardsSize[i]];
            byte temporaryCardsSize = 0;
            for(int j = 0; j < node.cardsSize[i]; j++) {
                if(node.cards[i][j] / FIGURE_NUMBER == node.atu) {
                    temporaryCards[temporaryCardsSize] = node.cards[i][j];
                    temporaryCardsSize++;
                }
            }
            for(int j = 0; j < node.cardsSize[i]; j++) {
                if(node.cards[i][j] / FIGURE_NUMBER != node.atu) {
                    temporaryCards[temporaryCardsSize] = node.cards[i][j];
                    temporaryCardsSize++;
                }
            }
            for(int j = 0; j < node.cardsSize[i]; j++) {
                node.cards[i][j] = temporaryCards[j];
            }
        }
    }

}
