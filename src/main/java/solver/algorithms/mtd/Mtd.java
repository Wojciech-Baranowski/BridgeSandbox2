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
        Node node = new Node(game);
        byte bestOutcome = mtd(node, (byte) ((byte) game.getStartingNumberOfCardsPerPlayer() / 2));
        return Result.mapResponseToResult(game, node.allOutcomeCards, bestOutcome);
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

    private byte mtd(Node node, byte f) {
        byte g = f;
        byte upperBound = 100;
        byte lowerBound = -100;
        byte beta;
        while (lowerBound < upperBound) {
            beta = (byte) max(g, lowerBound + 1);
            node.alpha = (byte) (beta - 1);
            node.beta = beta;
            g = alphaBeta(node);
            if (g < beta) {
                upperBound = g;
            } else {
                lowerBound = g;
            }
        }
        return g;
    }

    private byte alphaBeta(Node node) {
        if (node.depth == Node.allCardsNumber) {
            numberOfVisitedNodes++;
            if (node.allOutcomeCards[node.nsPoints][0] == -1) {
                for (int i = 0; i < Node.allCardsNumber; i++) {
                    node.allOutcomeCards[node.nsPoints][i] = node.allPlayedCards[i];
                }
            }
            return (byte) (node.nsPoints * node.color);
        }
        byte bestScore = (byte) (-100);
        for (byte i = 0; i < node.cardsSize[node.currentPlayer]; i++) {
            if (node.isCardValid(i)) {
                bestScore = (byte) max(bestScore, -playCard(node, i));
                node.alpha = (byte) max(node.alpha, bestScore);
                if (node.alpha >= node.beta) {
                    break;
                }
            }
        }
        return bestScore;
    }

    private byte playCard(Node node, byte currentCardIndex) {
        byte response;
        byte prevAlpha = node.alpha;
        byte prevBeta = node.beta;
        node.playCard(currentCardIndex);
        if (node.playedCardsSize != PLAYER_NUMBER) {
            response = alphaBeta(node);
        } else {
            byte[] playedCards = {node.playedCards[0], node.playedCards[1], node.playedCards[2], node.playedCards[3]};
            byte lastStartingPlayer = node.startingPlayer;
            node.summarize();
            response = alphaBeta(node);
            node.revertSummarize(playedCards, lastStartingPlayer);
        }
        node.revertPlayCard(currentCardIndex);
        node.alpha = prevAlpha;
        node.beta = prevBeta;
        return response;
    }

}
