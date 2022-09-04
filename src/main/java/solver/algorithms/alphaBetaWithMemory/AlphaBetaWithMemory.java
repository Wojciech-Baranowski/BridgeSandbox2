package solver.algorithms.alphaBetaWithMemory;

import gameLogic.game.Game;
import solver.Algorithm;
import solver.result.Result;

import java.util.Map;
import java.util.TreeMap;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class AlphaBetaWithMemory implements Algorithm {

    private long numberOfVisitedNodes;
    private Map<NodeInfo, Bound> memory;

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        Node node = new Node(game);
        memory = new TreeMap<>(NodeInfo::compareTo);
        byte bestOutcome = alphaBetaWithMemory(node);
        return Result.mapResponseToResult(game, node.allOutcomeCards, bestOutcome);
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

    private byte alphaBetaWithMemory(Node node) {
        byte alphaOrig = node.alpha;
        Bound memorizedBound;
        memorizedBound = memory.get(new NodeInfo(node));
        if (memorizedBound != null) {
            if (memorizedBound.flag == 0) {
                return memorizedBound.bound;
            } else if (memorizedBound.flag == -1) {
                node.alpha = (byte) max(node.alpha, memorizedBound.bound);
            } else if (memorizedBound.flag == 1) {
                node.beta = (byte) min(node.beta, memorizedBound.bound);
            }
            if (node.alpha >= node.beta) {
                return memorizedBound.bound;
            }
        }
        if (node.depth == Node.allCardsNumber) {
            numberOfVisitedNodes++;
            if (node.allOutcomeCards[node.nsPoints][0] == -1) {
                for (int i = 0; i < solver.algorithms.alphaBeta.Node.allCardsNumber; i++) {
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
        Bound bound = new Bound(bestScore);
        if (bestScore <= alphaOrig) {
            bound.flag = 1;
        } else if (bestScore >= node.beta) {
            bound.flag = -1;
        } else {
            bound.flag = 0;
        }
        memory.put(new NodeInfo(node), bound);
        return bestScore;
    }

    private byte playCard(Node node, byte currentCardIndex) {
        byte response;
        byte prevAlpha = node.alpha;
        byte prevBeta = node.beta;
        node.playCard(currentCardIndex);
        if (node.playedCardsSize != PLAYER_NUMBER) {
            response = alphaBetaWithMemory(node);
        } else {
            byte[] playedCards = {node.playedCards[0], node.playedCards[1], node.playedCards[2], node.playedCards[3]};
            byte lastStartingPlayer = node.startingPlayer;
            node.summarize();
            response = alphaBetaWithMemory(node);
            node.revertSummarize(playedCards, lastStartingPlayer);
        }
        node.revertPlayCard(currentCardIndex);
        node.alpha = prevAlpha;
        node.beta = prevBeta;
        return response;
    }

}
