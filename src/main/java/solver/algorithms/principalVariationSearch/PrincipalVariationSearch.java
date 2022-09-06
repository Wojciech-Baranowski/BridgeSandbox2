package solver.algorithms.principalVariationSearch;


import gameLogic.game.Game;
import solver.Algorithm;
import solver.result.Result;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.max;

public class PrincipalVariationSearch implements Algorithm {

    public long numberOfVisitedNodes;

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        Node node = new Node(game);
        byte bestOutcome = principalVariationSearch(node);
        return Result.mapResponseToResult(game, node.allOutcomeCards, bestOutcome);
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
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
                if (node.alpha >= node.beta) {
                    break;
                }
            }
        }
        return node.alpha;
    }

    protected byte playCard(Node node, byte currentCardIndex, byte alpha, byte beta) {
        byte response;
        byte prevAlpha = node.alpha;
        byte prevBeta = node.beta;
        node.playCard(currentCardIndex, alpha, beta);
        if (node.playedCardsSize != PLAYER_NUMBER) {
            response = principalVariationSearch(node);
        } else {
            byte[] playedCards = {node.playedCards[0], node.playedCards[1], node.playedCards[2], node.playedCards[3]};
            byte lastStartingPlayer = node.startingPlayer;
            node.summarize();
            response = principalVariationSearch(node);
            node.revertSummarize(playedCards, lastStartingPlayer);
        }
        node.revertPlayCard(currentCardIndex);
        node.alpha = prevAlpha;
        node.beta = prevBeta;
        return response;
    }

}
