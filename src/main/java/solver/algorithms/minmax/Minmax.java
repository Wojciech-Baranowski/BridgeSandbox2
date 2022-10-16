package solver.algorithms.minmax;

import gameLogic.game.Game;
import solver.Algorithm;
import solver.result.Result;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Minmax implements Algorithm {

    protected long numberOfVisitedNodes;

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        MinmaxNode minmaxNode = new MinmaxNode(game);
        byte bestScore = minMax(minmaxNode);
        return Result.mapResponseToResult(game, minmaxNode.allOutcomeCards, bestScore);
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

    protected byte minMax(MinmaxNode minmaxNode) {
        if (minmaxNode.depth == MinmaxNode.allCardsNumber) {
            numberOfVisitedNodes++;
            if (minmaxNode.allOutcomeCards[minmaxNode.nsPoints][0] == -1) {
                for (int i = 0; i < MinmaxNode.allCardsNumber; i++) {
                    minmaxNode.allOutcomeCards[minmaxNode.nsPoints][i] = minmaxNode.allPlayedCards[i];
                }
            }
            return minmaxNode.nsPoints;
        }
        byte bestScore = (byte) (minmaxNode.maximizing ? -100 : 100);
        for (byte i = 0; i < minmaxNode.cardsSize[minmaxNode.currentPlayer]; i++) {
            if (minmaxNode.isCardValid(i)) {
                if (minmaxNode.maximizing) {
                    bestScore = (byte) max(bestScore, playCard(minmaxNode, i));
                } else {
                    bestScore = (byte) min(bestScore, playCard(minmaxNode, i));
                }
            }
        }
        return bestScore;
    }

    protected byte playCard(MinmaxNode minmaxNode, byte currentCardIndex) {
        byte response;
        minmaxNode.playCard(currentCardIndex);
        if (minmaxNode.playedCardsSize != PLAYER_NUMBER) {
            response = minMax(minmaxNode);
        } else {
            byte[] playedCards = {
                    minmaxNode.playedCards[0],
                    minmaxNode.playedCards[1],
                    minmaxNode.playedCards[2],
                    minmaxNode.playedCards[3]};
            byte lastStartingPlayer = minmaxNode.startingPlayer;
            minmaxNode.summarize();
            boolean parity = minmaxNode.isSummarizeParity(lastStartingPlayer);
            if (parity) {
                minmaxNode.playDummyCard();
                response = minMax(minmaxNode);
                minmaxNode.revertPlayDummyCard();
            } else {
                response = minMax(minmaxNode);
            }
            minmaxNode.revertSummarize(playedCards, lastStartingPlayer);
        }
        minmaxNode.revertPlayCard(currentCardIndex);
        return response;
    }

}
