package solver.algorithms.alphaBetaWithMemory;

import gameLogic.game.Game;
import solver.Algorithm;
import solver.algorithms.alphaBeta.AlphaBetaNode;
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
        AlphaBetaWithMemoryNode alphaBetaWithMemoryNode = new AlphaBetaWithMemoryNode(game);
        memory = new TreeMap<>(NodeInfo::compareTo);
        byte bestOutcome = alphaBetaWithMemory(alphaBetaWithMemoryNode);
        return Result.mapResponseToResult(game, alphaBetaWithMemoryNode.allOutcomeCards, bestOutcome);
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

    private byte alphaBetaWithMemory(AlphaBetaWithMemoryNode alphaBetaWithMemoryNode) {
        byte alphaOrig = alphaBetaWithMemoryNode.alpha;
        Bound memorizedBound;
        synchronized (this) {
            memorizedBound = memory.get(new NodeInfo(alphaBetaWithMemoryNode));
        }
        if (memorizedBound != null) {
            if (memorizedBound.flag == 0) {
                return memorizedBound.bound;
            } else if (memorizedBound.flag == -1) {
                alphaBetaWithMemoryNode.alpha = (byte) max(alphaBetaWithMemoryNode.alpha, memorizedBound.bound);
            } else if (memorizedBound.flag == 1) {
                alphaBetaWithMemoryNode.beta = (byte) min(alphaBetaWithMemoryNode.beta, memorizedBound.bound);
            }
            if (alphaBetaWithMemoryNode.alpha >= alphaBetaWithMemoryNode.beta) {
                return memorizedBound.bound;
            }
        }
        if (alphaBetaWithMemoryNode.depth == AlphaBetaWithMemoryNode.allCardsNumber) {
            numberOfVisitedNodes++;
            if (alphaBetaWithMemoryNode.allOutcomeCards[alphaBetaWithMemoryNode.nsPoints][0] == -1) {
                for (int i = 0; i < AlphaBetaNode.allCardsNumber; i++) {
                    alphaBetaWithMemoryNode.allOutcomeCards[alphaBetaWithMemoryNode.nsPoints][i] = alphaBetaWithMemoryNode.allPlayedCards[i];
                }
            }
            return (byte) (alphaBetaWithMemoryNode.nsPoints * alphaBetaWithMemoryNode.color);
        }
        byte bestScore = (byte) (-100);
        for (byte i = 0; i < alphaBetaWithMemoryNode.cardsSize[alphaBetaWithMemoryNode.currentPlayer]; i++) {
            if (alphaBetaWithMemoryNode.isCardValid(i)) {
                bestScore = (byte) max(bestScore, -playCard(alphaBetaWithMemoryNode, i));
                alphaBetaWithMemoryNode.alpha = (byte) max(alphaBetaWithMemoryNode.alpha, bestScore);
                if (alphaBetaWithMemoryNode.alpha >= alphaBetaWithMemoryNode.beta) {
                    break;
                }
            }
        }
        Bound bound = new Bound(bestScore);
        if (bestScore <= alphaOrig) {
            bound.flag = 1;
        } else if (bestScore >= alphaBetaWithMemoryNode.beta) {
            bound.flag = -1;
        } else {
            bound.flag = 0;
        }
        synchronized (this) {
            memory.put(new NodeInfo(alphaBetaWithMemoryNode), bound);
        }
        return bestScore;
    }

    private byte playCard(AlphaBetaWithMemoryNode alphaBetaWithMemoryNode, byte currentCardIndex) {
        byte response;
        byte prevAlpha = alphaBetaWithMemoryNode.alpha;
        byte prevBeta = alphaBetaWithMemoryNode.beta;
        byte prevColor = alphaBetaWithMemoryNode.color;
        alphaBetaWithMemoryNode.playCard(currentCardIndex);
        if (alphaBetaWithMemoryNode.playedCardsSize != PLAYER_NUMBER) {
            response = alphaBetaWithMemory(alphaBetaWithMemoryNode);
        } else {
            byte[] playedCards = {
                    alphaBetaWithMemoryNode.playedCards[0],
                    alphaBetaWithMemoryNode.playedCards[1],
                    alphaBetaWithMemoryNode.playedCards[2],
                    alphaBetaWithMemoryNode.playedCards[3]};
            byte lastStartingPlayer = alphaBetaWithMemoryNode.startingPlayer;
            alphaBetaWithMemoryNode.summarize();
            if (alphaBetaWithMemoryNode.isSummarizeParity(lastStartingPlayer)) {
                alphaBetaWithMemoryNode.playDummyCard();
                response = (byte) -alphaBetaWithMemory(alphaBetaWithMemoryNode);
                alphaBetaWithMemoryNode.revertPlayDummyCard();
            } else {
                response = alphaBetaWithMemory(alphaBetaWithMemoryNode);
            }
            alphaBetaWithMemoryNode.revertSummarize(playedCards, lastStartingPlayer);
        }
        alphaBetaWithMemoryNode.revertPlayCard(currentCardIndex);
        alphaBetaWithMemoryNode.alpha = prevAlpha;
        alphaBetaWithMemoryNode.beta = prevBeta;
        alphaBetaWithMemoryNode.color = prevColor;
        return response;
    }

}
