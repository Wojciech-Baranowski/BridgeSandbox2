package solver.algorithms.PVSWithCutoff;

import gameLogic.game.Game;
import solver.algorithms.PVSKillerHeuristic.PVSKillerHeuristicAtuAndHighestFirst;
import solver.algorithms.principalVariationSearch.Node;
import solver.result.Result;

import static java.lang.Math.max;

public class PVSWithCutoff extends PVSKillerHeuristicAtuAndHighestFirst {

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        Node node = new Node(game);
        atu = node.atu;
        moveHighestAndAtuToFirstPosition(node);
        byte bestOutcome = principalVariationSearch(node);
        return Result.mapResponseToResult(game, node.allOutcomeCards, bestOutcome);
    }

    protected byte principalVariationSearch(Node node) {
        if (node.depth == Node.allCardsNumber) {
            numberOfVisitedNodes++;
            if (node.allOutcomeCards[node.nsPoints][0] == -1) {
                for (int i = 0; i < solver.algorithms.alphaBeta.Node.allCardsNumber; i++) {
                    node.allOutcomeCards[node.nsPoints][i] = node.allPlayedCards[i];
                }
            }
            return (byte) (node.nsPoints * node.color);
        }
        byte score = -100;
        for (byte i = 0; i < node.cardsSize[node.currentPlayer]; i++) {
            if (node.isCardValid(i)) {
                if (score == -100) {
                    score = (byte) -playCard(node, i, (byte) -node.beta, (byte) -node.alpha);
                } else {
                    score = (byte) -playCard(node, i, (byte) (-node.alpha - 1), (byte) -node.alpha);
                    if (node.alpha < score && score < node.beta) {
                        score = (byte) -playCard(node, i, (byte) -node.beta, (byte) -score);
                    }
                }
                node.alpha = (byte) max(node.alpha, score);
                if ((node.alpha >= node.beta)
                        || ((node.color == 1) && (Node.allCardsNumber - node.depth + 3) / 4 <= score - node.nsPoints)
                        || (node.color == -1) && (0 >= -score - node.nsPoints)) {
                    break;
                }
            }
        }
        return node.alpha;
    }

}
