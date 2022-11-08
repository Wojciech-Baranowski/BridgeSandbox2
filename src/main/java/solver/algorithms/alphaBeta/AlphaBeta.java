package solver.algorithms.alphaBeta;

import gameLogic.game.Game;
import solver.Algorithm;
import solver.result.Result;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.abs;
import static java.lang.Math.max;

public class AlphaBeta implements Algorithm {

    private long numberOfVisitedNodes;

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        AlphaBetaNode alphaBetaNode = new AlphaBetaNode(game);
        byte bestOutcome = alphaBeta(alphaBetaNode);
        return Result.mapResponseToResult(game, alphaBetaNode.allOutcomeCards, bestOutcome);
    }

    public boolean canNSTakeAll(Game game, int numberOfCardsToTake) {
        numberOfVisitedNodes = 0;
        AlphaBetaNode alphaBetaNode = new AlphaBetaNode(game, numberOfCardsToTake);
        return abs(alphaBeta(alphaBetaNode)) >= numberOfCardsToTake;
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

    private byte alphaBeta(AlphaBetaNode alphaBetaNode) {
        if (alphaBetaNode.depth == AlphaBetaNode.allCardsNumber) {
            numberOfVisitedNodes++;
            if (alphaBetaNode.allOutcomeCards[alphaBetaNode.nsPoints][0] == -1) {
                for (int i = 0; i < AlphaBetaNode.allCardsNumber; i++) {
                    alphaBetaNode.allOutcomeCards[alphaBetaNode.nsPoints][i] = alphaBetaNode.allPlayedCards[i];
                }
            }
            return (byte) (alphaBetaNode.nsPoints * alphaBetaNode.color);
        }
        byte bestScore = (byte) (-100);
        for (byte i = 0; i < alphaBetaNode.cardsSize[alphaBetaNode.currentPlayer]; i++) {
            if (alphaBetaNode.isCardValid(i)) {
                bestScore = (byte) max(bestScore, -playCard(alphaBetaNode, i));
                alphaBetaNode.alpha = (byte) max(alphaBetaNode.alpha, bestScore);
                if (alphaBetaNode.alpha >= alphaBetaNode.beta) {
                    break;
                }
            }
        }
        return bestScore;
    }

    private byte playCard(AlphaBetaNode alphaBetaNode, byte currentCardIndex) {
        byte response;
        byte prevAlpha = alphaBetaNode.alpha;
        byte prevBeta = alphaBetaNode.beta;
        byte prevColor = alphaBetaNode.color;
        alphaBetaNode.playCard(currentCardIndex);
        if (alphaBetaNode.playedCardsSize != PLAYER_NUMBER) {
            response = alphaBeta(alphaBetaNode);
        } else {
            byte[] playedCards = {
                    alphaBetaNode.playedCards[0],
                    alphaBetaNode.playedCards[1],
                    alphaBetaNode.playedCards[2],
                    alphaBetaNode.playedCards[3]};
            byte lastStartingPlayer = alphaBetaNode.startingPlayer;
            alphaBetaNode.summarize();
            if (alphaBetaNode.isSummarizeParity(lastStartingPlayer)) {
                alphaBetaNode.playDummyCard();
                response = (byte) -alphaBeta(alphaBetaNode);
                alphaBetaNode.revertPlayDummyCard();
            } else {
                response = alphaBeta(alphaBetaNode);
            }
            alphaBetaNode.revertSummarize(playedCards, lastStartingPlayer);
        }
        alphaBetaNode.revertPlayCard(currentCardIndex);
        alphaBetaNode.alpha = prevAlpha;
        alphaBetaNode.beta = prevBeta;
        alphaBetaNode.color = prevColor;
        return response;
    }
}