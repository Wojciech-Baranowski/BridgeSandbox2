package solver.algorithms.mtd;

import gameLogic.game.Game;
import solver.Algorithm;
import solver.result.Result;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.max;

public class Mtd implements Algorithm {

    protected long numberOfVisitedNodes;

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        MtdNode mtdNode = new MtdNode(game);
        byte bestOutcome = mtd(mtdNode, (byte) ((byte) game.getStartingNumberOfCardsPerPlayer() / 2));
        return Result.mapResponseToResult(game, mtdNode.allOutcomeCards, bestOutcome);
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

    private byte mtd(MtdNode mtdNode, byte f) {
        byte g = f;
        byte upperBound = 100;
        byte lowerBound = -100;
        byte beta;
        while (lowerBound < upperBound) {
            beta = (byte) max(g, lowerBound + 1);
            mtdNode.alpha = (byte) (beta - 1);
            mtdNode.beta = beta;
            g = alphaBeta(mtdNode);
            if (g < beta) {
                upperBound = g;
            } else {
                lowerBound = g;
            }
        }
        return g;
    }

    private byte alphaBeta(MtdNode mtdNode) {
        if (mtdNode.depth == MtdNode.allCardsNumber) {
            numberOfVisitedNodes++;
            if (mtdNode.allOutcomeCards[mtdNode.nsPoints][0] == -1) {
                for (int i = 0; i < MtdNode.allCardsNumber; i++) {
                    mtdNode.allOutcomeCards[mtdNode.nsPoints][i] = mtdNode.allPlayedCards[i];
                }
            }
            return (byte) (mtdNode.nsPoints * mtdNode.color);
        }
        byte bestScore = (byte) (-100);
        for (byte i = 0; i < mtdNode.cardsSize[mtdNode.currentPlayer]; i++) {
            if (mtdNode.isCardValid(i)) {
                bestScore = (byte) max(bestScore, -playCard(mtdNode, i));
                mtdNode.alpha = (byte) max(mtdNode.alpha, bestScore);
                if (mtdNode.alpha >= mtdNode.beta) {
                    break;
                }
            }
        }
        return bestScore;
    }

    private byte playCard(MtdNode mtdNode, byte currentCardIndex) {
        byte response;
        byte prevAlpha = mtdNode.alpha;
        byte prevBeta = mtdNode.beta;
        mtdNode.playCard(currentCardIndex);
        if (mtdNode.playedCardsSize != PLAYER_NUMBER) {
            response = alphaBeta(mtdNode);
        } else {
            byte[] playedCards = {
                    mtdNode.playedCards[0],
                    mtdNode.playedCards[1],
                    mtdNode.playedCards[2],
                    mtdNode.playedCards[3]};
            byte lastStartingPlayer = mtdNode.startingPlayer;
            mtdNode.summarize();
            response = alphaBeta(mtdNode);
            mtdNode.revertSummarize(playedCards, lastStartingPlayer);
        }
        mtdNode.revertPlayCard(currentCardIndex);
        mtdNode.alpha = prevAlpha;
        mtdNode.beta = prevBeta;
        return response;
    }

}
