package solver.algorithms.principalVariationSearch;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;
import solver.algorithms.alphaBeta.AlphaBetaNode;
import solver.result.Result;

import static java.lang.Math.max;

public class PVSWithCutoff extends PVSKillerHeuristicAtuAndHighestFirst {

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        PVSNode pvsNode = new PVSNode(game);
        atu = pvsNode.atu;
        moveHighestAndAtuToFirstPosition(pvsNode);
        byte bestOutcome = principalVariationSearch(pvsNode);
        return Result.mapResponseToResult(game, pvsNode.allOutcomeCards, bestOutcome);
    }

    protected byte principalVariationSearch(PVSNode pvsNode) {
        if (pvsNode.depth == BaseNode.allCardsNumber) {
            numberOfVisitedNodes++;
            if (pvsNode.allOutcomeCards[pvsNode.nsPoints][0] == -1) {
                for (int i = 0; i < AlphaBetaNode.allCardsNumber; i++) {
                    pvsNode.allOutcomeCards[pvsNode.nsPoints][i] = pvsNode.allPlayedCards[i];
                }
            }
            return (byte) (pvsNode.nsPoints * pvsNode.color);
        }
        byte score = -100;
        for (byte i = 0; i < pvsNode.cardsSize[pvsNode.currentPlayer]; i++) {
            if (pvsNode.isCardValid(i)) {
                if (score == -100) {
                    score = (byte) -playCard(pvsNode, i, (byte) -pvsNode.beta, (byte) -pvsNode.alpha);
                } else {
                    score = (byte) -playCard(pvsNode, i, (byte) (-pvsNode.alpha - 1), (byte) -pvsNode.alpha);
                    if (pvsNode.alpha < score && score < pvsNode.beta) {
                        score = (byte) -playCard(pvsNode, i, (byte) -pvsNode.beta, (byte) -score);
                    }
                }
                pvsNode.alpha = (byte) max(pvsNode.alpha, score);
                if ((pvsNode.alpha >= pvsNode.beta)
                        || ((pvsNode.color == 1)
                        && (BaseNode.allCardsNumber - pvsNode.depth + 3) / 4 <= score - pvsNode.nsPoints)
                        || (pvsNode.color == -1)
                        && (0 >= -score - pvsNode.nsPoints)) {
                    break;
                }
            }
        }
        return pvsNode.alpha;
    }

}
